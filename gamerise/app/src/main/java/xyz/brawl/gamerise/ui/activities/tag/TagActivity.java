package xyz.brawl.gamerise.ui.activities.tag;

import static xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.data.user.User;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.model.repository.user.UserRepository;
import xyz.brawl.gamerise.ui.activities.main.MainActivity;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModel;
import xyz.brawl.gamerise.ui.viewmodels.tag.TagViewModelFactory;
import xyz.brawl.gamerise.util.NetworkUtil;
import xyz.brawl.gamerise.util.ServiceLocator;
import xyz.brawl.gamerise.model.Result;

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
    private ImageButton checkboxButton;
    private FrameLayout noInternetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_tag);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TagRepository tagRepository =
                ServiceLocator.getInstance().getTagRepository(getApplication(),
                        getApplication().getResources().getBoolean(R.bool.debug_mode));

        UserRepository userRepository =
                ServiceLocator.getInstance().getUserRepository(getApplication());

        tagViewModel = new ViewModelProvider(
                this,
                new TagViewModelFactory(tagRepository, userRepository)).get(TagViewModel.class);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();


        noInternetView = findViewById(R.id.no_internet_view);
        if(!NetworkUtil.isInternetAvailable(this)){
            noInternetView.setVisibility(View.VISIBLE);
        }

        insertTag = findViewById(R.id.insertTag);
        insertTag.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (handlerInvioTag()) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
            return false;
        });


        ImageButton searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handlerInvioTag()) {
                    Intent intent = new Intent(TagActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button googleLogInButton = findViewById(R.id.btnGoogleCustom);
        Button googleLogOutButton = findViewById(R.id.btnLogoutGoogleCustom);
        googleLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogOutButton.setVisibility(View.VISIBLE);
                googleLogOutButton.setClickable(true);
                googleLogInButton.setVisibility(View.GONE);
                googleLogInButton.setClickable(false);
            }
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


        googleLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogOutButton.setVisibility(View.GONE);
                googleLogOutButton.setClickable(false);
                googleLogInButton.setVisibility(View.VISIBLE);
                googleLogInButton.setClickable(true);
            }
        });

        //test animation checkbox
        checkboxButton = findViewById(R.id.checkbox_button);
        checkboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checked) {
                    checkboxButton.setImageResource(R.drawable.baseline_check_box_24);
                    checked = true;
                } else {
                    checkboxButton.setImageResource(R.drawable.baseline_check_box_outline_blank_24);
                    checked = false;
                }
            }
        });

        startIntentSenderForResult = new ActivityResultContracts.StartIntentSenderForResult();

        activityResultLauncher = registerForActivityResult(startIntentSenderForResult, activityResult -> {
            if (activityResult.getResultCode() == Activity.RESULT_OK) {
                Log.d(TAG, "result.getResultCode() == Activity.RESULT_OK");
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(activityResult.getData());
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate with Firebase.
                        tagViewModel.getGoogleUserMutableLiveData(idToken).observe(this, authenticationResult -> {
                            if (authenticationResult.isSuccess()) {
                                User user = (User) ((Result.Success) authenticationResult).getData();
                                //saveLoginData(user.getEmail(), null, user.getIdToken());
                                Log.i(TAG, "Logged as: " + user.getName());
                                tagViewModel.setAuthenticationError(false);
                                dimmiDoveEQuando();
                            } else {
                                tagViewModel.setAuthenticationError(true);
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



        /// La visualizzazione della lista dei tag potrebbe dare problemi
        /*RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TagAdapter tagAdapter = new TagAdapter(new ArrayList<>());
        recyclerView.setAdapter(tagAdapter);*/

        // Osserva i cambiamenti nei tag
        /*tagViewModel.getTags().observe(this, updatedTags -> {
            if (updatedTags != null && tagAdapter != null) {
                tagAdapter.updateTags(updatedTags); // Aggiungi o aggiorna i tag
            }
        });*/
    }


    /**
     * Gestisce l'invio del tag tramite il ViewModel.
     * Richiede al tagViewModel di aggiornare la lista di tag se il tag è valido.
     * Richiede al tagViewModel di effettuare la Query all'API se il tag non è presente nel database.
     * Richiede al tagViewModel
     *
     * @return true se il tag è valido, false altrimenti.
     */

    private boolean handlerInvioTag() {
        String inputTag = insertTag.getText().toString().trim();
        GameAccountSingleton.getInstance().setUserTag(inputTag);
        if (tagViewModel.isTagValid(inputTag)) {
            if (checked) {
                Tag newTag = new Tag("Nuovo Giocatore", inputTag);
                tagViewModel.insertTag(newTag);// Salva il tag sia nella memoria che nel database2
            }
            insertTag.setText(""); // Resetta il campo di testo
            Toast.makeText(TagActivity.this, "Tag aggiunto!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(TagActivity.this, "Tag non valido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void dimmiDoveEQuando() {
        if (handlerInvioTag()) {
            Intent intent = new Intent(TagActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}