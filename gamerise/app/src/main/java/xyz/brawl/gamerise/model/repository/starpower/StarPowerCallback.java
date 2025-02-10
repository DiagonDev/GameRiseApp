package xyz.brawl.gamerise.model.repository.starpower;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;

public interface StarPowerCallback {
    void onSuccessFromRemote(List<StarPowerEntry> starPower);
    void onFailureFromRemote(Exception errorMessage);

    //qui è giusto
    void onSuccessFromLocal(List<StarPowerEntry> starPowerList);
    void onFailureFromLocal(Exception exception);
}
