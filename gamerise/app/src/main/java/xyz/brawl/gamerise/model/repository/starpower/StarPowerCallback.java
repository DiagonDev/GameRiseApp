package xyz.brawl.gamerise.model.repository.starpower;

import java.util.List;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;

public interface StarPowerCallback {
    //TODO: leo deve modificare qui
    void onSuccessFromRemote(List<StarPowerEntry> starPowerList, long lastUpdate);
    void onFailureFromRemote(String errorMessage);

    //qui è giusto
    void onSuccessFromLocal(List<StarPowerEntry> starPowerList);
    void onFailureFromLocal(Exception exception);
}
