package xyz.brawl.gamerise.model.repository.user;

import xyz.brawl.gamerise.model.data.user.User;

public interface UserCallBack {
    void onSuccessFromAuthentication(User user);
    void onFailureFromAuthentication(String message);
    void onSuccessFromRemoteDatabase(User user);
    void onSuccessFromGettingUserPreferences();
    void onFailureFromRemoteDatabase(String message);
    void onSuccessLogout();
}
