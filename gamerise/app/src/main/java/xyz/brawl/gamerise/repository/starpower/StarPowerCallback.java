package xyz.brawl.gamerise.repository.starpower;

import java.util.List;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;

public interface StarPowerCallback {
    void onSuccessFromRemote(List<StarPowerEntry> starPower);
    void onFailureFromRemote(Exception errorMessage);
    void onSuccessFromLocal(List<StarPowerEntry> starPowerList);
    void onFailureFromLocal(Exception exception);
}
