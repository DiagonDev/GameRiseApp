package xyz.brawl.gamerise.model.repository.battlelog;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleLogRepository extends AbstractRepository implements IBattleLogRepository {
    public BattleLogRepository(Application application, ResponseCallback responseCallback) {
        super(application, responseCallback);
    }

    /**
     *
     * @param playerTag tag del giocatore, per ora impostato su teo
     * @return null provvisorio
     */
    @Override
    public void fetchBattleLog(String playerTag) {
        get(apiService.getBattlelog(playerTag));
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

}

