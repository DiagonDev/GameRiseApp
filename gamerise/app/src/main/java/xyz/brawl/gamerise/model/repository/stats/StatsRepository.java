package xyz.brawl.gamerise.model.repository.stats;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.stats.BaseStatsLocalDataSource;
import xyz.brawl.gamerise.model.data.stat.Stat;

public class StatsRepository implements StatsCallBack{
    private final BaseStatsLocalDataSource statsLocalDataSource;
    private final MutableLiveData<Result> statsLiveData;

    public StatsRepository(BaseStatsLocalDataSource statsLocalDataSource) {
        statsLiveData = new MutableLiveData<>();
        this.statsLocalDataSource = statsLocalDataSource;
        this.statsLocalDataSource.setStatsCallback(this);
    }
    public MutableLiveData<Result> fetchStats(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API

        } else {
            statsLocalDataSource.getStats(tagId);
        }
        return statsLiveData;
    }


    @Override
    public void onSuccessFromRemote(Stat stats, long lastUpdate) {
        
    }

    @Override
    public void onFailureFromRemote(String errorMessage) {

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
