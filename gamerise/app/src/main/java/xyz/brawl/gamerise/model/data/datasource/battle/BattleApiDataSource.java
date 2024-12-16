package xyz.brawl.gamerise.model.data.datasource.battle;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.datasource.AbstractApiDataSource;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleApiDataSource extends AbstractApiDataSource {

    public BattleApiDataSource(Context context, ResponseCallback responseCallback) {
        super(context, responseCallback);
    }


    /**
     *
     * @param playerTag tag del giocatore, per ora impostato su teo
     * @return null provvisorio
     */
    public void fetchBattleLog(String playerTag) {
        get(apiService.getBattlelog(playerTag));
    }

    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof BattleLogApiResponse) {
            BattleLogApiResponse blar = (BattleLogApiResponse) response.body();
            //leo - responseCallback.onSuccess(response.body().getBattleResponseList(), response.raw().receivedResponseAtMillis());
            List<BattleLogEntry> battleLogEntries = blar.getBattleResponseList();

            Log.d("SUCCESS", battleLogEntries.toString());
            //leo - più output tanto per esser sicuri
                    /*ale - tolto perchè allunga il debug
                    for (BattleLogEntry battleLogEntry : battleLogEntries)
                        Log.d("MAP", battleLogEntry.getEvent().getMap());
                      //*/
            responseCallback.onSuccess(battleLogEntries, response.raw().receivedResponseAtMillis());
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }
}
