package xyz.brawl.gamerise.repository.starpower;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.source.starpower.BaseStarPowerLocalDataSource;
import xyz.brawl.gamerise.source.starpower.BaseStarPowerRemoteDataSource;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class StarPowerRepository implements StarPowerCallback {
    private final BaseStarPowerLocalDataSource starPowerLocalDataSource;
    private final BaseStarPowerRemoteDataSource starPowerRemoteDataSource;
    private final MutableLiveData<Result> allStarPowerLiveData;

    public StarPowerRepository(BaseStarPowerLocalDataSource starPowerLocalDataSource, BaseStarPowerRemoteDataSource starPowerRemoteDataSource) {
        allStarPowerLiveData = new MutableLiveData<>();
        this.starPowerLocalDataSource = starPowerLocalDataSource;
        this.starPowerRemoteDataSource = starPowerRemoteDataSource;
        this.starPowerLocalDataSource.setStarPowerCallback(this);
        this.starPowerRemoteDataSource.setStarPowerCallback(this);
    }

    public MutableLiveData<Result> fetchStarPower(Long brawlerId, boolean connected, String tagId) {
        if (connected && !GameAccountSingleton.getInstance().isChecked()) {
           starPowerRemoteDataSource.getStarPowerList(tagId);
        } else {
            starPowerLocalDataSource.getStarPower(brawlerId);
        }
        return allStarPowerLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<StarPowerEntry> starPowerList) {
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
