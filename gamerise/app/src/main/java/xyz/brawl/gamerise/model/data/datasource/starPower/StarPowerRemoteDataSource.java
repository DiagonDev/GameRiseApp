package xyz.brawl.gamerise.model.data.datasource.starPower;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.service.ApiService;

public class StarPowerRemoteDataSource extends BaseStarPowerRemoteDataSource{
    private final ApiService apiService;
    private final PlayerMapper playerMapper = new PlayerMapper();
    public StarPowerRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getStarPowerList(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(Call<PlayerApiResponse> call, Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<StarPowerEntry> starPowerEntryList = playerMapper.mapToStarPowers(response.body());
                    // Passa il risultato e il tempo di aggiornamento al callback
                    starPowerCallback.onSuccessFromRemote(starPowerEntryList, response.raw().receivedResponseAtMillis());
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    starPowerCallback.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(Call<PlayerApiResponse> call, Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                starPowerCallback.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
