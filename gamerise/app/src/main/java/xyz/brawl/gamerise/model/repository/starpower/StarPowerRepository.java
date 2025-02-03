package xyz.brawl.gamerise.model.repository.starpower;

import static xyz.brawl.gamerise.model.data.battle.BattleMapper.mapToBattleLogEntries;
import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.datasource.starPower.BaseStarPowerLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.starPower.StarPowerLocalDataSource;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;

public class StarPowerRepository implements StarPowerCallback {
    private final BaseStarPowerLocalDataSource starPowerLocalDataSource;
    private final MutableLiveData<Result> allStarPowerLiveData;

    public StarPowerRepository(BaseStarPowerLocalDataSource starPowerLocalDataSource) {
        allStarPowerLiveData = new MutableLiveData<>();
        this.starPowerLocalDataSource = starPowerLocalDataSource;
        this.starPowerLocalDataSource.setStarPowerCallback(this);
    }

    public MutableLiveData<Result> fetchStarPower(Long brawlerId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
        } else {
            starPowerLocalDataSource.getStarPower(brawlerId);
        }
        return allStarPowerLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<StarPowerEntry> starPowerList, long lastUpdate) {

    }

    @Override
    public void onFailureFromRemote(String errorMessage) {

    }

    @Override
    public void onSuccessFromLocal(List<StarPowerEntry> starPowerList) {
        Result result = new Result.Success(starPowerList);
        allStarPowerLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allStarPowerLiveData.postValue(resultError);
    }
}
