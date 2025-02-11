package xyz.brawl.gamerise.model.data.datasource.user;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import xyz.brawl.gamerise.model.data.user.GoogleUser;

/**
 * Class that manages the user authentication using Firebase Authentication.
 */
public class UserAuthenticationFirebaseDataSource extends BaseUserAuthenticationRemoteDataSource {

    private static final String TAG = UserAuthenticationFirebaseDataSource.class.getSimpleName();

    private final FirebaseAuth firebaseAuth;

    public UserAuthenticationFirebaseDataSource() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public GoogleUser getLoggedUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            return null;
        } else {
            return new GoogleUser(firebaseUser.getDisplayName(), firebaseUser.getUid());
        }
    }

    @Override
    public void logout() {
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    firebaseAuth.removeAuthStateListener(this);
                    Log.d(TAG, "User logged out");
                    userResponseCallback.onSuccessLogout();
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseAuth.signOut();
    }

    @Override
    public void signInWithGoogle(String sessionId) {
        if (sessionId !=  null) {
            // Got an ID token from Google. Use it to authenticate with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(sessionId, null);
            firebaseAuth.signInWithCredential(firebaseCredential).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        userResponseCallback.onSuccessFromAuthentication(
                                new GoogleUser(firebaseUser.getDisplayName(),
                                        firebaseUser.getUid()
                                )
                        );
                    } else {
                        userResponseCallback.onFailureFromAuthentication(
                                getErrorMessage(task.getException()));
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
                }
            });
        }
    }

    private String getErrorMessage(Exception exception) {
        if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            return "Credenziali Errate";
        } else if (exception instanceof FirebaseAuthInvalidUserException) {
            return "Utente non valido";
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            return "Errore di collisione";
        }
        return "Unexpected Error";
    }
}
