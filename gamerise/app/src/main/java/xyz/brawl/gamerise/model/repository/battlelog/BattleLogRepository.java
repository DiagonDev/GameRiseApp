package xyz.brawl.gamerise.model.repository.battlelog;

import android.content.Context;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.service.BattleLogApiService;
import xyz.brawl.gamerise.model.service.ServiceLocator;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BattleLogRepository implements IBattleLogRepository {

    private BattleLogApiService battleLogApiService;
    private Context context;
    private ResponseCallback responseCallback;

    public BattleLogRepository(Context context, ResponseCallback responseCallback) {
        this.battleLogApiService = ServiceLocator.getInstance().getBattleLogApiService();
        this.context = context;
        this.responseCallback = responseCallback;
    }

    @Override
    public void fetchBattleLog(String playerTag, long lastUpdate) {
        Call<BattleLogApiResponse> call = battleLogApiService.getBattlelog2(playerTag);
        call.enqueue(new Callback<BattleLogApiResponse>() {
            @Override
            public void onResponse
                    (Call<BattleLogApiResponse> call, Response<BattleLogApiResponse> response) {
                //TODO: cambiare messaggio di risposta
                if (response.body() != null && response.isSuccessful() && response.message().equals("OK")) {
                    List<BattleLogEntry> battleLogEntries = response.body().getBattleResponseList();

                    Log.d("TAG", battleLogEntries.toString());
                    //TODO: implement data base
                }
                else {
                    responseCallback.onFailure(context.getString(R.string.error_message));
                    Log.d("TAG", "NO RESPONSE");
                }
            }

            @Override
            public void onFailure(Call<BattleLogApiResponse> call, Throwable throwable) {
                responseCallback.onFailure(throwable.getMessage());
                Log.d("TAG", "NO RESPONSE");
            }
        });

    }
}
