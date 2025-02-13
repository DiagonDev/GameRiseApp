package xyz.brawl.gamerise.repository.stats;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.stat.Stat;
import xyz.brawl.gamerise.source.stats.BaseStatsLocalDataSource;
import xyz.brawl.gamerise.source.stats.BaseStatsRemoteDataSource;
import xyz.brawl.gamerise.util.GameAccountSingleton;

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
    public MutableLiveData<Result> fetchStats(String tagId, boolean connected) {
        if (connected && !GameAccountSingleton.getInstance().isChecked()) {
            statsRemoteDataSource.getStats(tagId);
        } else {
            statsLocalDataSource.getStats(tagId);
        }
        return statsLiveData;
    }

    public MutableLiveData<Result> insertStats(Stat statsToInsert){
        statsLocalDataSource.insertStats(statsToInsert);
        return statsLiveData;
    }

    @Override
    public void onSuccessFromRemote(Stat stats, long lastUpdate) {
        if(GameAccountSingleton.getInstance().isChecked())
            statsLocalDataSource.insertStats(stats);
        else{
            Result result = new Result.Success(stats);
            statsLiveData.postValue(result);
        }
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
