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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
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
import xyz.brawl.gamerise.model.GoogleUser;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.repository.player.PlayerRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;
import xyz.brawl.gamerise.repository.user.UserRepository;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModelFactory;
import xyz.brawl.gamerise.util.GameAccountSingleton;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;

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
    private Button googleLogInButton, googleLogOutButton;
    private boolean connected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tag);

        insertTag = findViewById(R.id.insertTag);
        savedTagTextView = findViewById(R.id.savedTagTextView);
        searchButton = findViewById(R.id.searchButton);

        googleLogInButton = findViewById(R.id.btnGoogleCustom);
        googleLogOutButton = findViewById(R.id.btnLogoutGoogleCustom);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TagRepository tagRepository =
                ServiceLocator.getInstance().getTagRepository(getApplication());
        PlayerRepository playerRepository =
                ServiceLocator.getInstance().getPlayerRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));
        UserRepository userRepository =
                ServiceLocator.getInstance().getUserRepository();

        tagViewModel = new ViewModelProvider(
                this,
                new TagViewModelFactory(tagRepository, userRepository, playerRepository)).get(TagViewModel.class);

        // controllo se c'è già un utente loggato con google
        if (tagViewModel.getLoggedGoogleUser() != null) {
            Log.d(TAG, "Already logged in as: " + tagViewModel.getLoggedGoogleUser().getName());
            googleLogOutButton.setVisibility(View.VISIBLE);
            googleLogOutButton.setClickable(true);
            googleLogInButton.setVisibility(View.GONE);
            googleLogInButton.setClickable(false);
        }

        if (!NetworkUtil.isInternetAvailable(this)) {
            new NoInternetDialogFragment().show(getSupportFragmentManager(), "NoInternetDialog");
            connected = false;
            searchButton.setClickable(false);
            searchButton.setVisibility(View.GONE);
            googleLogOutButton.setClickable(false);
            googleLogOutButton.setVisibility(View.GONE);
            googleLogInButton.setClickable(false);
            googleLogInButton.setVisibility(View.GONE);
        }

        tagViewModel.fetchTag().observe(this, result -> {
            if (result.isSuccess()) {
                Tag savedTag = (Tag) ((Result.Success) result).getData();
                if (savedTag != null) {
                    savedTagTextView.setText(savedTag.getTag());
                    // quando salvo una tag in locale, se l'utente è loggato con google, la salvo anche su firebase
                    if (tagViewModel.getLoggedGoogleUser() != null)
                        tagViewModel.saveTagOnFirebase(savedTag.getTag(), tagViewModel.getLoggedGoogleUser().getSessionId());
                }
            }
        });

        savedTagTextView.setOnClickListener(view -> {
            GameAccountSingleton.getInstance().setLastUpdate(0);
            GameAccountSingleton.getInstance().setChecked(true);
            String savedTag = savedTagTextView.getText().toString().trim();
            if (handlerInvioTag(savedTag, true)) {
                Intent intent = new Intent(TagActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        insertTag.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

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
            GameAccountSingleton.getInstance().setLastUpdate(0);
            String inputTag = insertTag.getText().toString().trim();
            if (handlerInvioTag(inputTag, checked)) {
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
            GameAccountSingleton.getInstance().setChecked(checked); // Assuming checked is true for both cases
        });

        setupGoogleButtons();
    }

    public boolean handlerInvioTag(String inputTag, boolean isNewTag) {
        inputTag = inputTag.trim();
        if (inputTag.startsWith("#")) {
            inputTag = inputTag.substring(1);
        }

        GameAccountSingleton.getInstance().setUserTag(inputTag);

        // If it's a new tag, validate and add it
        if (isNewTag) {
            if (tagViewModel.isTagValid(inputTag)) {
                Tag newTag = new Tag("Nuovo Giocatore", inputTag);
                tagViewModel.insertTag(newTag, connected);
                insertTag.setText("");
                Toast.makeText(TagActivity.this, "Tag aggiunto!", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            return true;
        }
    }

    private void setupGoogleButtons() {
        oneTapClient = Identity.getSignInClient(this);
        startIntentSenderForResult = new ActivityResultContracts.StartIntentSenderForResult();
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
        activityResultLauncher = registerForActivityResult(startIntentSenderForResult, activityResult -> {
            if (activityResult.getResultCode() == Activity.RESULT_OK) {
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(activityResult.getData());
                    String sessionId = credential.getGoogleIdToken();
                    if (sessionId != null) {
                        tagViewModel.getGoogleUserMutableLiveData(sessionId).observe(this, authenticationResult -> {
                            if (authenticationResult.isSuccess()) {
                                GoogleUser googleUser = (GoogleUser) ((Result.Success) authenticationResult).getData();
                                Log.i(TAG, "Logged as: " + googleUser.getName());
                                googleLogOutButton.setVisibility(View.VISIBLE);
                                googleLogOutButton.setClickable(true);
                                googleLogInButton.setVisibility(View.GONE);
                                googleLogInButton.setClickable(false);
                                handleFirebaseTag(googleUser);
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

        googleLogInButton.setOnClickListener(view -> {
            googleLogOutButton.setVisibility(View.VISIBLE);
            googleLogOutButton.setClickable(true);
            googleLogInButton.setVisibility(View.GONE);
            googleLogInButton.setClickable(false);
            oneTapClient.beginSignIn(signInRequest)
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
                    });
        });
        googleLogOutButton.setOnClickListener(view -> {
            googleLogOutButton.setVisibility(View.GONE);
            googleLogOutButton.setClickable(false);
            googleLogInButton.setVisibility(View.VISIBLE);
            googleLogInButton.setClickable(true);
            tagViewModel.logoutGoogleUser();
        });
    }

    // called when user logs in with google to get his tag from firebase
    private void handleFirebaseTag(GoogleUser googleUser) {
        boolean isGoogleUserLoggedIn = tagViewModel.getLoggedGoogleUser() != null;
        if (isGoogleUserLoggedIn)
            try {
                tagViewModel.getFirebaseTag_TagVM(googleUser.getSessionId()).observe(this, result -> {
                    if (result != null && result.isSuccess() && result instanceof Result.Success) {
                        String tagFirebase = (String) ((Result.Success) result).getData();

                        // caso 0: ho una tag su Firebase: uso quella
                        if (tagFirebase != null && !tagFirebase.equals("null")) {
                            GameAccountSingleton.getInstance().setLastUpdate(0);
                            GameAccountSingleton.getInstance().setChecked(true);
                            handlerInvioTag(tagFirebase, true);
                            Log.d(TAG, "ho settato " + tagFirebase);
                            Intent intent = new Intent(TagActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        // caso 2: tagFirebase == null && tag esiste nel DB
                        else if (tagFirebase == null || tagViewModel.fetchTag().getValue() != null) {
                            GameAccountSingleton.getInstance().setLastUpdate(0);
                            GameAccountSingleton.getInstance().setChecked(true);
                            tagViewModel.fetchTag().observe(this, localTag -> {
                                if (localTag.isSuccess()) {
                                    Tag castedTag = (Tag) ((Result.Success) localTag).getData();
                                    if (castedTag != null)
                                        tagViewModel.saveTagOnFirebase(castedTag.getTag(), googleUser.getSessionId());

                                    new AlertDialog.Builder(this)
                                            .setTitle("Tag Saved")
                                            .setMessage("La tag salvata sul telefono è stata salvata anche su Firebase")
                                            .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // Dismiss when "OK" is pressed
                                            .show();
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
    }
}
