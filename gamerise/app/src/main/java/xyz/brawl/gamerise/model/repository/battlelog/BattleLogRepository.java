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
    //TODO: aggiongere il REMOTE
    public BattleLogRepository(BaseBattleLocalDataSource battleLocalDataSource, BaseBattleRemoteDataSource battleRemoteDataSource) {
        allBattleLogLiveData = new MutableLiveData<>();
        this.battleLocalDataSource = battleLocalDataSource;
        this.battleRemoteDataSource = battleRemoteDataSource;
        this.battleLocalDataSource.setBattleLogCallback(this);
        this.battleRemoteDataSource.setBattleLogCallback(this);
    }

    /**
     * playerTag tag del giocatore, per ora impostato su teo
     * @return null provvisorio
     */

    public MutableLiveData<Result> fetchBattleLog(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            battleRemoteDataSource.getBattleLog(tagId);
        } else {
            battleLocalDataSource.getBattles(tagId);
        }
        return allBattleLogLiveData;
    }


    /// Questo metodo serve per permettere ai repository di gestire le risposte API in maniera differente
    /*@Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof BattleLogApiResponse) {
            BattleLogApiResponse blar = (BattleLogApiResponse) response.body();
            responseCallback.onSuccess(blar.getBattleResponseList(), response.raw().receivedResponseAtMillis());
            *//* TODO: implementare la cosa
            List<Battle> battles = BattleMapper.mapToBattles(blar.getBattleResponseList());
            responseCallback.onSuccess(battles, response.raw().receivedResponseAtMillis());*//*
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }*/

    @Override
    public void onSuccessFromRemote(List<Battle> battles, long lastUpdate) {
        battleLocalDataSource.insertBattles(battles);
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBattleLogLiveData.postValue(resultError);
    }

    //TODO: da completare
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

