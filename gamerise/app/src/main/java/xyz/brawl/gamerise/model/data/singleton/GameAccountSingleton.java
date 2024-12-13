package xyz.brawl.gamerise.model.data.singleton;

public class GameAccountSingleton {
    private static GameAccountSingleton instance;

    //TODO: aggiungere Singleton dentro TagActivity
    private String UserTag;
    private GameAccountSingleton() {
    }

    public static GameAccountSingleton getInstance() {
        if (instance == null) {
            instance = new GameAccountSingleton();
        }
        return instance;
    }

    public String getUserTag() {
        return UserTag;
    }

    public void setUserTag(String userTag) {
        UserTag = userTag;
    }
}
