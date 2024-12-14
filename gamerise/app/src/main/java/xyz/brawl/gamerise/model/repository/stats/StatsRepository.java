package xyz.brawl.gamerise.model.repository.stats;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;
import xyz.brawl.gamerise.model.service.ServiceLocator;
import xyz.brawl.gamerise.model.service.StatsApiService;
import xyz.brawl.gamerise.util.ResponseCallback;

public class StatsRepository implements IStatsRepository {

    private StatsApiService statsApiService;
    private Context context;
    private ResponseCallback responseCallback;

    public StatsRepository(Context context, ResponseCallback responseCallback) {
        this.statsApiService = ServiceLocator.getInstance().getStatsApiService();
        this.context = context;
        this.responseCallback = responseCallback;
    }

    @Override
    public void fetchStats(String playerTag,long lastUpdate) {
        Call<StatsApiResponse> call = statsApiService.getPlayer2(playerTag);
        call.enqueue(new Callback<StatsApiResponse>() {
            @Override
            public void onResponse(Call<StatsApiResponse> call, Response<StatsApiResponse> response) {
                if (response.body() != null && response.isSuccessful() && response.message().equals("OK")) {
                    int _3vs3Victories = response.body().get3vs3Victories();
                    int trophies = response.body().getTrophies();
                    int expLevel = response.body().getExpLevel();
                    int highestTrophies = response.body().getHighestTrophies();
                    int rank = response.body().getRank();
                    int soloVictories = response.body().getSoloVictories();
                    int duoVictories = response.body().getDuoVictories();

                    //TODO: implement data base
                }
                else {
                    responseCallback.onFailure(context.getString(R.string.error_message));
                }
            }

            public void onFailure(Call<StatsApiResponse> call, Throwable throwable) {
                responseCallback.onFailure(throwable.getMessage());
            }
        });
    }
}