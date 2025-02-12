package xyz.brawl.gamerise.ui.activities.tag;

import static xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.snackbar.Snackbar;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.data.user.GoogleUser;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.model.repository.user.UserRepository;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

/// Se guardate il logCat vedrete generarsi un warnining al crearsi di questa classe
/// è dovuto al fatto che non avendo item nel recycler view, l'inflate non riesce a trovare
/// il colore da applicare. Non è un problema bloccante e si risolve appena popoliamo il recycler
public class TagActivity extends AppCompatActivity {

    private ActivityResultLauncher<IntentSenderRequest> activityResultLauncher;
    private ActivityResultContracts.StartIntentSenderForResult startIntentSenderForResult;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private boolean checked = false;
    private TagViewModel tagViewModel;
    private EditText insertTag;
    private TextView savedTagTextView;
    private ImageButton searchButton;
    private ImageButton checkboxButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tag);

        insertTag = findViewById(R.id.insertTag);
        savedTagTextView = findViewById(R.id.savedTagTextView);
        searchButton = findViewById(R.id.searchButton);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TagRepository tagRepository =
                ServiceLocator.getInstance().getTagRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));
        PlayerRepository playerRepository =
                ServiceLocator.getInstance().getPlayerRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));
        UserRepository userRepository =
                ServiceLocator.getInstance().getUserRepository();

        tagViewModel = new ViewModelProvider(
                this,
                new TagViewModelFactory(tagRepository, userRepository, playerRepository)).get(TagViewModel.class);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id)) // server id
                        .setFilterByAuthorizedAccounts(false) // solo gli account già sul telefono
                        .build())
                .setAutoSelectEnabled(true) // auto login se c'è solo un account
                .build();

        Button googleLogInButton = findViewById(R.id.btnGoogleCustom);
        Button googleLogOutButton = findViewById(R.id.btnLogoutGoogleCustom);
        googleLogInButton.setOnClickListener(view -> {
            googleLogOutButton.setVisibility(View.VISIBLE);
            googleLogOutButton.setClickable(true);
            googleLogInButton.setVisibility(View.GONE);
            googleLogInButton.setClickable(false);
        });

        googleLogInButton.setOnClickListener(v -> oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, result -> {
                    Log.d(TAG, "onSuccess from oneTapClient.beginSignIn(BeginSignInRequest)");
                    IntentSenderRequest intentSenderRequest =
                            new IntentSenderRequest.Builder(result.getPendingIntent()).build();
                    activityResultLauncher.launch(intentSenderRequest);
                })
                .addOnFailureListener(this, e -> {
                    // No saved credentials found. Launch the One Tap sign-up flow, or
                    // do nothing and continue presenting the signed-out UI.
                    Log.d(TAG, e.getLocalizedMessage());
                    Snackbar.make(findViewById(android.R.id.content), e.toString(), Snackbar.LENGTH_SHORT).show();
                }));

        googleLogOutButton.setOnClickListener(view -> {
            googleLogOutButton.setVisibility(View.GONE);
            googleLogOutButton.setClickable(false);
            googleLogInButton.setVisibility(View.VISIBLE);
            googleLogInButton.setClickable(true);
        });

        FrameLayout noInternetView = findViewById(R.id.no_internet_view);
        if(!NetworkUtil.isInternetAvailable(this)){
            noInternetView.setVisibility(View.VISIBLE);
        }

        tagViewModel.fetchTag().observe(this, result -> {
            if (result.isSuccess()) {
                Tag savedTag = (Tag) ((Result.Success) result).getData();
                if (savedTag != null)
                    savedTagTextView.setText(savedTag.getTag());
            }
        });

        savedTagTextView.setOnClickListener(view -> {
            String savedTag = savedTagTextView.getText().toString().trim();
            if (settamiStoTag(savedTag, true)) {
                Intent intent = new Intent(TagActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        insertTag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String upperCaseText = s.toString().toUpperCase();
                if (!s.toString().equals(upperCaseText)) {
                    insertTag.setText(upperCaseText);
                    insertTag.setSelection(upperCaseText.length());
                }
            }
        });

        insertTag.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (keyEvent != null && keyEvent.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER &&
                            keyEvent.getAction() == android.view.KeyEvent.ACTION_DOWN)) {
                searchButton.performClick();
                return true;
            }
            return false;
        });

        searchButton.setOnClickListener(view -> {
            String inputTag = insertTag.getText().toString().trim();
            if (settamiStoTag(inputTag, checked)) {
                Intent intent = new Intent(TagActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        checkboxButton = findViewById(R.id.checkbox_button);
        checkboxButton.setOnClickListener(view -> {
            if (!checked) {
                checkboxButton.setImageResource(R.drawable.baseline_check_box_24);
                checked = true;
            } else {
                checkboxButton.setImageResource(R.drawable.baseline_check_box_outline_blank_24);
                checked = false;
            }
        });

        startIntentSenderForResult = new ActivityResultContracts.StartIntentSenderForResult();

        activityResultLauncher = registerForActivityResult(startIntentSenderForResult, activityResult -> {
            if (activityResult.getResultCode() == Activity.RESULT_OK) {
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(activityResult.getData());
                    String sessionId = credential.getGoogleIdToken();
                    if (sessionId !=  null) {
                        tagViewModel.getGoogleUserMutableLiveData(sessionId).observe(this, authenticationResult -> {
                            if (authenticationResult.isSuccess()) {
                                GoogleUser googleUser = (GoogleUser) ((Result.Success) authenticationResult).getData();
                                Log.i(TAG, "Logged as: " + googleUser.getName());
                                postGoogleLogin(googleUser);
                            } else {
                                Snackbar.make(findViewById(android.R.id.content),
                                        "",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        });

                    }
                } catch (ApiException e) {
                    Snackbar.make(findViewById(android.R.id.content),
                            e.toString(),
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void postGoogleLogin(GoogleUser googleUser) {
        String tempTag = insertTag.getText().toString();

        try {
            tagViewModel.getFirebaseTag_TagVM(googleUser.getSessionId()).observe(this, result -> {
                if (result != null && result.isSuccess() && result instanceof Result.Success) {

                    String tagFirebase = (String) ((Result.Success) result).getData();

                    // caso 0: ho una tag su Firebase: uso quella
                    if (tagFirebase != null && !tagFirebase.equals("null")) {
                        settamiStoTag(tagFirebase, true);
                        Log.d(TAG, "ho settato " + tagFirebase);
                    }

                    // caso 1: tagFirebase == null && insertTag esiste
                    if ((tagFirebase == null || tagFirebase.equals("null")) && (insertTag != null && !tempTag.isEmpty())) {
                        Log.d(TAG, "provo a salvare la tag " + tempTag + "su Firebase per " + googleUser.getName());
                        tagViewModel.saveTagOnFirebase(tempTag, googleUser.getSessionId());
                    }

                    // caso 2: tagFirebase == null && tag esiste nel DB
                    if (tagFirebase == null || tagFirebase.equals("null")) {
                        tagViewModel.fetchTag().observe(this, localTag -> {
                            if (localTag.isSuccess()) {
                                Tag castedTag = (Tag) ((Result.Success) localTag).getData();
                                if (castedTag != null)
                                    tagViewModel.saveTagOnFirebase(castedTag.getTag(), googleUser.getSessionId());
                            }
                        });
                    }

                } else if (result instanceof Result.Error) {
                    String errorMessage = ((Result.Error) result).getMessage();
                    Log.d("UserTag", "Failure: " + errorMessage);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(TagActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean settamiStoTag(String tag, boolean shouldSave) {
        if (tag.startsWith("#")) tag = tag.substring(1);

        Log.d(TAG, "Tag inserito: " + tag + " isvalid:" + tagViewModel.isTagValid(tag));
        // se il tag che dovrei salvare è uguale a quello già salvato, non faccio niente invece
        Log.d("salva", "GAS: " + GameAccountSingleton.getInstance().getUserTag() + " tag: " + tag + " shouldSave: " + shouldSave);

        //       if (GameAccountSingleton.getInstance().getUserTag() != null &&
        //                !GameAccountSingleton.getInstance().getUserTag().equals(tag) && shouldSave)
        if (shouldSave)
                tagViewModel.insertTag(new Tag("Nuovo Giocatore", tag));

        GameAccountSingleton.getInstance().setUserTag(tag);
        GameAccountSingleton.getInstance().setChecked(shouldSave);

        if (tagViewModel.isTagValid(tag))
            Toast.makeText(TagActivity.this, "Tag aggiunto! " + GameAccountSingleton.getInstance().getUserTag(), Toast.LENGTH_LONG).show();
        else Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();

        return tagViewModel.isTagValid(tag);
    }
}
