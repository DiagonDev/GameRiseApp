package xyz.brawl.gamerise.model.data.datasource.stats;

import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.service.ApiService;

public class StatsRemoteDataSource extends BaseStatsRemoteDataSource{

    private final ApiService apiService;
    public StatsRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getStats(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlayerApiResponse> call, @NonNull Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    // Passa il risultato e il tempo di aggiornamento al callback
                    statsCallBack.onSuccessFromRemote(PlayerMapper.mapToStat(response.body()), response.raw().receivedResponseAtMillis());
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    statsCallBack.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerApiResponse> call, @NonNull Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                statsCallBack.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
