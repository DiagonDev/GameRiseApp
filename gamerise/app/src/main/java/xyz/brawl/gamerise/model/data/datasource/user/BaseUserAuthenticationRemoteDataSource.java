package xyz.brawl.gamerise.model.data.datasource.user;

import xyz.brawl.gamerise.model.data.user.User;
import xyz.brawl.gamerise.model.repository.user.UserCallBack;
/**
 * Base class to manage the user authentication.
 */
public abstract class BaseUserAuthenticationRemoteDataSource {
    protected UserCallBack userResponseCallback;

    public void setUserResponseCallback(UserCallBack userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }
    public abstract User getLoggedUser();
    public abstract void logout();
    public abstract void signInWithGoogle(String sessionId);
}
