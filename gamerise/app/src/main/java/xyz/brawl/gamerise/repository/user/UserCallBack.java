package xyz.brawl.gamerise.repository.user;

import xyz.brawl.gamerise.model.GoogleUser;

public interface UserCallBack {
    void onSuccessFromAuthentication(GoogleUser googleUser);
    void onFailureFromAuthentication(String message);
    void onSuccessFromRemoteDatabase(GoogleUser googleUser);
    void onSuccessFromRemoteDatabase(String tag);
    void onFailureFromRemoteDatabase(String error);
    void onSuccessLogout();
}
