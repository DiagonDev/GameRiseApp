package xyz.brawl.gamerise.repository.battlelog;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIME;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.source.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.source.battle.BaseBattleRemoteDataSource;
import xyz.brawl.gamerise.util.GameAccountSingleton;


public class BattleLogRepository implements BattleLogCallback {
    private final MutableLiveData<Result> allBattleLogLiveData;
    private final BaseBattleLocalDataSource battleLocalDataSource;
    private final BaseBattleRemoteDataSource battleRemoteDataSource;

    public BattleLogRepository(BaseBattleLocalDataSource battleLocalDataSource, BaseBattleRemoteDataSource battleRemoteDataSource) {
        allBattleLogLiveData = new MutableLiveData<>();
        this.battleLocalDataSource = battleLocalDataSource;
        this.battleRemoteDataSource = battleRemoteDataSource;
        this.battleLocalDataSource.setBattleLogCallback(this);
        this.battleRemoteDataSource.setBattleLogCallback(this);
    }

    public MutableLiveData<Result> fetchBattleLog(String tagId, boolean connected, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        Log.d("TEST", "Current time: "+currentTime);
        Log.d("TEST", "SingletonTime: "+ lastUpdate);

        if (connected && (currentTime - lastUpdate) > FRESH_TIME)
            battleRemoteDataSource.getBattleLog(tagId);
        else
            battleLocalDataSource.getBattles(tagId);

        return allBattleLogLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<Battle> battles, long lastUpdate) {
        if (GameAccountSingleton.getInstance().isChecked())
            battleLocalDataSource.insertBattles(battles);
        else {
            Result result = new Result.Success(battles);
            allBattleLogLiveData.postValue(result);
        }
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBattleLogLiveData.postValue(resultError);
    }

    @Override
    public void onSuccessFromLocal(List<Battle> battles) {
        Result result = new Result.Success(battles);
        allBattleLogLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBattleLogLiveData.postValue(resultError);
    }
}

