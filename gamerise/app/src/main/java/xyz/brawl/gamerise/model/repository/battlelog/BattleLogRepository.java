package xyz.brawl.gamerise.model.repository.battlelog;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

import javax.xml.transform.Result;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleLogRepository implements BattleLogCallback {

    //private static final String TAG = BattleLogRepository.class.getSimpleName();
    private final MutableLiveData<Result> allBattleLogLiveData;

    private final BaseBattleLocalDataSource battleLocalDataSource;

    public BattleLogRepository(BaseBattleLocalDataSource battleLocalDataSource) {
        allBattleLogLiveData = new MutableLiveData<>();
        this.battleLocalDataSource = battleLocalDataSource;
        this.battleLocalDataSource.setBattleLogCallback(this);
    }

    /**
     * @param playerTag tag del giocatore, per ora impostato su teo
     * @return null provvisorio
     */

    public MutableLiveData<Result> fetchArticles(String country, int page, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //articleRemoteDataSource.getArticles(country);
            //get(apiService.getBattlelog(playerTag));
        } else {
            battleLocalDataSource.getArticles();
        }

        return allArticlesMutableLiveData;
    }


    /// Questo metodo serve per permettere ai repository di gestire le risposte API in maniera differente
    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof BattleLogApiResponse) {
            BattleLogApiResponse blar = (BattleLogApiResponse) response.body();
            responseCallback.onSuccess(blar.getBattleResponseList(), response.raw().receivedResponseAtMillis());
            /* TODO: implementare la cosa
            List<Battle> battles = BattleMapper.mapToBattles(blar.getBattleResponseList());
            responseCallback.onSuccess(battles, response.raw().receivedResponseAtMillis());*/
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }

    public void insertBattle(Battle battle) {
        battleLogDataSource.insertBattle(battle);
    }

    public void deleteBattle(Battle battle) {
        battleLogDataSource.deleteBattle(battle);
    }

    @Override
    public void onSuccessFromRemote(List<Battle> battles, long lastUpdate) {

    }

    @Override
    public void onFailureFromRemote(String errorMessage) {

    }

    @Override
    public void onSuccessFromLocal(List<Battle> battles) {

    }

    @Override
    public void onFailureFromLocal(Exception exception) {

    }
}

