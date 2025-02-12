package xyz.brawl.gamerise.source.starPower;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;
import xyz.brawl.gamerise.service.ApiService;

public class StarPowerRemoteDataSource extends BaseStarPowerRemoteDataSource{
    private final ApiService apiService;
    public StarPowerRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getStarPowerList(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlayerApiResponse> call, @NonNull Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<StarPowerEntry> starPowerEntryList = PlayerMapper.mapToStarPowers(response.body());
                    // Passa il risultato e il tempo di aggiornamento al callback
                    starPowerCallback.onSuccessFromRemote(starPowerEntryList);
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    starPowerCallback.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerApiResponse> call, @NonNull Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                starPowerCallback.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
