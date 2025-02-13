package xyz.brawl.gamerise.source.starpower;

import xyz.brawl.gamerise.repository.starpower.StarPowerCallback;

public abstract class BaseStarPowerRemoteDataSource {
    protected StarPowerCallback starPowerCallback;
    public void setStarPowerCallback(StarPowerCallback starPowerCallback) {
        this.starPowerCallback = starPowerCallback;
    }
    public abstract void getStarPowerList(String tagId);
}
