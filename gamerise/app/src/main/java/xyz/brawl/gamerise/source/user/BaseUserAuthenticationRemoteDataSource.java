package xyz.brawl.gamerise.source.user;

import xyz.brawl.gamerise.model.GoogleUser;
import xyz.brawl.gamerise.repository.user.UserCallBack;
/**
 * Base class to manage the user authentication.
 */
public abstract class BaseUserAuthenticationRemoteDataSource {
    protected UserCallBack userResponseCallback;

    public void setUserResponseCallback(UserCallBack userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }
    public abstract GoogleUser getLoggedUser();
    public abstract void logout();
    public abstract void signInWithGoogle(String sessionId);
}
