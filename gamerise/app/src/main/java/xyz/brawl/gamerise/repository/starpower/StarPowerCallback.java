package xyz.brawl.gamerise.repository.starpower;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.StarPowerEntry;

public interface StarPowerCallback {
    void onSuccessFromRemote(List<StarPowerEntry> starPower);
    void onFailureFromRemote(Exception errorMessage);

    //qui è giusto
    void onSuccessFromLocal(List<StarPowerEntry> starPowerList);
    void onFailureFromLocal(Exception exception);
}
