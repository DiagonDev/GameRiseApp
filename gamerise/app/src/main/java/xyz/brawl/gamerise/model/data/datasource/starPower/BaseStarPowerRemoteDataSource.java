package xyz.brawl.gamerise.model.data.datasource.starPower;

import xyz.brawl.gamerise.model.repository.starpower.StarPowerCallback;

public abstract class BaseStarPowerRemoteDataSource {
    protected StarPowerCallback starPowerCallback;
    public void setStarPowerCallback(StarPowerCallback starPowerCallback) {
        this.starPowerCallback = starPowerCallback;
    }
    public abstract void getStarPowerList(String tagId);
}
