package xyz.brawl.gamerise.model.data.singleton;

public class GameAccountSingleton {
    private static GameAccountSingleton instance;

    private String UserTag;
    private boolean checked;
    private long lastUpdate;

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
