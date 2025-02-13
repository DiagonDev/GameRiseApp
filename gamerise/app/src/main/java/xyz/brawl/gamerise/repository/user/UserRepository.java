package xyz.brawl.gamerise.repository.user;

import androidx.lifecycle.MutableLiveData;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.source.user.BaseUserAuthenticationRemoteDataSource;
import xyz.brawl.gamerise.source.user.BaseUserTagRemoteDataSource;
import xyz.brawl.gamerise.model.GoogleUser;

public class UserRepository implements UserCallBack {

    private final BaseUserAuthenticationRemoteDataSource userRemoteDataSource;
    private final BaseUserTagRemoteDataSource userDataRemoteDataSource;
    private final MutableLiveData<Result> userAuthLiveData;
    private final MutableLiveData<Result> userTagLiveData = new MutableLiveData<>();

    public UserRepository(BaseUserAuthenticationRemoteDataSource userRemoteDataSource,
                          BaseUserTagRemoteDataSource userDataRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.userDataRemoteDataSource = userDataRemoteDataSource;
        this.userAuthLiveData = new MutableLiveData<>();
        this.userRemoteDataSource.setUserResponseCallback(this);
        this.userDataRemoteDataSource.setUserResponseCallback(this);
    }

    public MutableLiveData<Result> getGoogleUser(String token) {
        signInWithGoogle(token);
        return userAuthLiveData;
    }

    public MutableLiveData<Result> getUserTag$UserRepository(String sessionId) {
        userDataRemoteDataSource.getUserTag(sessionId);
        return userTagLiveData;
    }

    public GoogleUser getLoggedUser() {
        return userRemoteDataSource.getLoggedUser();
    }

    public MutableLiveData<Result> logout() {
        userRemoteDataSource.logout();
        return userAuthLiveData;
    }

    public void signInWithGoogle(String sessionId) {
        userRemoteDataSource.signInWithGoogle(sessionId);
    }

    public void saveUserTag(String tag, String sessionId) {
        userDataRemoteDataSource.saveUserTag(tag, sessionId);
    }

    public void onSuccessFromAuthentication(GoogleUser googleUser) {
        if (googleUser != null) {
            userDataRemoteDataSource.saveUserDataOnFirebaseDB(googleUser);
        }
    }

    public void onFailureFromAuthentication(String message) {
        Result.Error result = new Result.Error(message);
        userAuthLiveData.postValue(result);
    }

    @Override
    public void onSuccessFromRemoteDatabase(GoogleUser googleUser) {
        userAuthLiveData.postValue(new Result.Success(googleUser));
    }

    @Override
    public void onSuccessFromRemoteDatabase(String tag) {
        userTagLiveData.postValue(new Result.Success(tag));
    }


    @Override
    public void onFailureFromRemoteDatabase(String error) {
        Result.Error result = new Result.Error(error);
        userAuthLiveData.postValue(result);
    }

    @Override
    public void onSuccessLogout() {

    }

}
