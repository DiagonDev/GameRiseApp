package xyz.brawl.gamerise.model.data.datasource.user;


import static xyz.brawl.gamerise.util.Constants.FIREBASE_REALTIME_DATABASE;
import static xyz.brawl.gamerise.util.Constants.FIREBASE_USERS_COLLECTION;
import static xyz.brawl.gamerise.util.Constants.FIREBASE_USER_TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import xyz.brawl.gamerise.model.data.user.GoogleUser;

/**
 * Class that gets the user information using Firebase Realtime Database.
 */
public class UserFirebaseDataSource extends BaseUserTagRemoteDataSource {

    private static final String TAG = UserFirebaseDataSource.class.getSimpleName();

    private final DatabaseReference databaseReference;

    public UserFirebaseDataSource() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DATABASE);
        databaseReference = firebaseDatabase.getReference().getRef();
    }

    @Override
    public void saveUserDataOnFirebaseDB(GoogleUser googleUser) {
        databaseReference.child(FIREBASE_USERS_COLLECTION).child(googleUser.getSessionId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.d(TAG, "User already present in Firebase Realtime Database");
                    userResponseCallback.onSuccessFromRemoteDatabase(googleUser);
                } else {
                    Log.d(TAG, "User not present in Firebase Realtime Database");
                    databaseReference.child(FIREBASE_USERS_COLLECTION).child(googleUser.getSessionId()).setValue(googleUser)
                            .addOnSuccessListener(avoid -> userResponseCallback.onSuccessFromRemoteDatabase(googleUser))
                            .addOnFailureListener(e -> userResponseCallback.onFailureFromRemoteDatabase(e.getLocalizedMessage()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userResponseCallback.onFailureFromRemoteDatabase(error.getMessage());
            }
        });
    }

    @Override
    public void getUserTag(String sessionId) {
        databaseReference.child(FIREBASE_USERS_COLLECTION).child(sessionId).
                child(FIREBASE_USER_TAG).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String tag = task.getResult().getValue(String.class);

                        userResponseCallback.onSuccessFromRemoteDatabase(tag);
                        Log.d(TAG, "Tag salvata su Firebase: " + tag);
                    }
                });
    }

    @Override
    public void saveUserTag(String tag, String sessionId) {
        databaseReference.child(FIREBASE_USERS_COLLECTION).child(sessionId).
                child(FIREBASE_USER_TAG).setValue(tag);
    }
}
