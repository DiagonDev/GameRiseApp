package xyz.brawl.gamerise.model.repository.user;

import xyz.brawl.gamerise.model.data.user.GoogleUser;

public interface UserCallBack {
    void onSuccessFromAuthentication(GoogleUser googleUser);
    void onFailureFromAuthentication(String message);
    void onSuccessFromRemoteDatabase(GoogleUser googleUser);
    void onSuccessFromRemoteDatabase(String tag);
    void onFailureFromRemoteDatabase(String error);
    void onSuccessLogout();
}
