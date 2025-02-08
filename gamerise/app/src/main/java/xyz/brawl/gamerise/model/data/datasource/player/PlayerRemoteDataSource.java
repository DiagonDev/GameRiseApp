package xyz.brawl.gamerise.model.data.datasource.player;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.service.ApiService;

public class PlayerRemoteDataSource extends BasePlayerRemoteDataSource {
    private final ApiService apiService;
    public PlayerRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getPlayerName(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(Call<PlayerApiResponse> call, Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String playerName = response.body().name;
                    // Passa il risultato e il tempo di aggiornamento al callback
                    playerCallBack.onSuccessFromRemote(playerName, response.raw().receivedResponseAtMillis());
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    playerCallBack.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(Call<PlayerApiResponse> call, Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                playerCallBack.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
