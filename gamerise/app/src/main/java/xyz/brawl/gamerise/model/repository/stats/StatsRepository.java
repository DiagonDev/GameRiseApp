package xyz.brawl.gamerise.model.repository.stats;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.stats.BaseStatsLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.stats.BaseStatsRemoteDataSource;
import xyz.brawl.gamerise.model.data.stat.Stat;

public class StatsRepository implements StatsCallBack {
    private final MutableLiveData<Result> statsLiveData;
    private final BaseStatsLocalDataSource statsLocalDataSource;
    private final BaseStatsRemoteDataSource statsRemoteDataSource;

    public StatsRepository(BaseStatsLocalDataSource statsLocalDataSource, BaseStatsRemoteDataSource statsRemoteDataSource) {
        statsLiveData = new MutableLiveData<>();
        this.statsRemoteDataSource = statsRemoteDataSource;
        this.statsLocalDataSource = statsLocalDataSource;
        this.statsLocalDataSource.setStatsCallback(this);
        this.statsRemoteDataSource.setStatsCallback(this);
    }
    public MutableLiveData<Result> fetchStats(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            statsRemoteDataSource.getStats(tagId);
        } else {
            statsLocalDataSource.getStats(tagId);
        }
        return statsLiveData;
    }


    @Override
    public void onSuccessFromRemote(Stat stats, long lastUpdate) {
        statsLocalDataSource.insertStats(stats);
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        statsLiveData.postValue(resultError);
    }

    @Override
    public void onSuccessFromLocal(Stat stats) {
        Result result = new Result.Success(stats);
        statsLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        statsLiveData.postValue(resultError);
    }
}
