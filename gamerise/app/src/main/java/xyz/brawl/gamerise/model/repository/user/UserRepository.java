package xyz.brawl.gamerise.model.repository.user;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.user.BaseUserAuthenticationRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.user.BaseUserDataRemoteDataSource;
import xyz.brawl.gamerise.model.data.user.User;


public class UserRepository implements UserCallBack {

    private final BaseUserAuthenticationRemoteDataSource userRemoteDataSource;
    private final BaseUserDataRemoteDataSource userDataRemoteDataSource;
    private final MutableLiveData<Result> userMutableLiveData;
    private final MutableLiveData<Result> userFavoriteNewsMutableLiveData;
    private final MutableLiveData<Result> userPreferencesMutableLiveData;

    public UserRepository(BaseUserAuthenticationRemoteDataSource userRemoteDataSource,
                          BaseUserDataRemoteDataSource userDataRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
        this.userDataRemoteDataSource = userDataRemoteDataSource;
        this.userMutableLiveData = new MutableLiveData<>();
        this.userPreferencesMutableLiveData = new MutableLiveData<>();
        this.userFavoriteNewsMutableLiveData = new MutableLiveData<>();
        this.userRemoteDataSource.setUserResponseCallback(this);
        this.userDataRemoteDataSource.setUserResponseCallback(this);
    }

    public MutableLiveData<Result> getGoogleUser(String sessionId) {
        signInWithGoogle(sessionId);
        return userMutableLiveData;
    }

    public MutableLiveData<Result> getUserTag(String sessionId) {
        userDataRemoteDataSource.getUserTag(sessionId);
        return userPreferencesMutableLiveData;
    }

    public User getLoggedUser() {
        return userRemoteDataSource.getLoggedUser();
    }

    public MutableLiveData<Result> logout() {
        userRemoteDataSource.logout();
        return userMutableLiveData;
    }

    public void signInWithGoogle(String token) {
        userRemoteDataSource.signInWithGoogle(token);
    }

    public void saveUserTag(String tag, String sessionId) {
        userDataRemoteDataSource.saveUserTag(tag, sessionId);
    }

    public void onSuccessFromAuthentication(User user) {
        if (user != null) {
            userDataRemoteDataSource.saveUserData(user);
        }
    }

    public void onFailureFromAuthentication(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    public void onSuccessFromRemoteDatabase(User user) {
        Result.Success result = new Result.Success(user);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onSuccessFromGettingUserPreferences() {
        userPreferencesMutableLiveData.postValue(new Result.Success(null));
    }

    @Override
    public void onFailureFromRemoteDatabase(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onSuccessLogout() {

    }

}
