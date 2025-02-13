package xyz.brawl.gamerise.source.starPower;

import xyz.brawl.gamerise.repository.starpower.StarPowerCallback;

public abstract class BaseStarPowerLocalDataSource {
    protected StarPowerCallback starPowerCallback;

    public void setStarPowerCallback(StarPowerCallback starPowerCallback) {
        this.starPowerCallback = starPowerCallback;
    }

    public abstract void getStarPower(Long brawlerId);
}
