package xyz.brawl.gamerise.model.repository.battlelog;

import static xyz.brawl.gamerise.model.data.battle.BattleMapper.mapToBattleLogEntries;
import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleRemoteDataSource;


public class BattleLogRepository implements BattleLogCallback {

    //private static final String TAG = BattleLogRepository.class.getSimpleName();
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

    public MutableLiveData<Result> fetchBattleLog(String tagId, boolean connected) {
        if (connected) {
            battleRemoteDataSource.getBattleLog(tagId);
        } else {
            battleLocalDataSource.getBattles(tagId);
        }
        return allBattleLogLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<Battle> battles, long lastUpdate) {
        battleLocalDataSource.insertBattles(battles);
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

