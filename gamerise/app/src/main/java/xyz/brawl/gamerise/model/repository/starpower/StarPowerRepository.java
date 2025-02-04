package xyz.brawl.gamerise.model.repository.starpower;

import static xyz.brawl.gamerise.model.data.battle.BattleMapper.mapToBattleLogEntries;
import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.starPower.BaseStarPowerLocalDataSource;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.datasource.starPower.BaseStarPowerRemoteDataSource;

public class StarPowerRepository implements StarPowerCallback {
    private final BaseStarPowerLocalDataSource starPowerLocalDataSource;
    private final BaseStarPowerRemoteDataSource starPowerRemoteDataSource;
    private final MutableLiveData<Result> allStarPowerLiveData;

    public StarPowerRepository(BaseStarPowerLocalDataSource starPowerLocalDataSource, BaseStarPowerRemoteDataSource starPowerRemoteDataSource) {
        this.starPowerRemoteDataSource = starPowerRemoteDataSource;
        allStarPowerLiveData = new MutableLiveData<>();
        this.starPowerLocalDataSource = starPowerLocalDataSource;
        this.starPowerLocalDataSource.setStarPowerCallback(this);
    }

    public MutableLiveData<Result> fetchStarPower(Long brawlerId, long lastUpdate, String tagId) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
           starPowerRemoteDataSource.getStarPowerList(tagId);
        } else {
            starPowerLocalDataSource.getStarPower(brawlerId);
        }
        return allStarPowerLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<StarPowerEntry> starPowerList, long lastUpdate) {
        Result result = new Result.Success(starPowerList);
        allStarPowerLiveData.postValue(result);
    }

    @Override
    public void onFailureFromRemote(Exception errorMessage) {
        Result.Error resultError = new Result.Error(errorMessage.getMessage());
        allStarPowerLiveData.postValue(resultError);
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
