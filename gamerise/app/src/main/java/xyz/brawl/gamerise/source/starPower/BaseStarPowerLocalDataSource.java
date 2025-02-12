package xyz.brawl.gamerise.source.starPower;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.repository.starpower.StarPowerCallback;

public abstract class BaseStarPowerLocalDataSource {
    protected StarPowerCallback starPowerCallback;

    public void setStarPowerCallback(StarPowerCallback starPowerCallback) {
        this.starPowerCallback = starPowerCallback;
    }

    public abstract void getStarPower(Long brawlerId);

    public abstract void insertStarPower(List<StarPowerEntry> starPowerEntryList);
}
