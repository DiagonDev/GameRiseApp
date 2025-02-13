package xyz.brawl.gamerise.source.brawler;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.service.ApiService;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;

public class BrawlerRemoteDataSource extends BaseBrawlersRemoteDataSource {
    private final ApiService apiService;
    public BrawlerRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getBrawlerList(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlayerApiResponse> call, @NonNull Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BrawlerEntry> brawlersList = PlayerMapper.mapToBrawlers(response.body());
                    // Passa il risultato e il tempo di aggiornamento al callback
                    brawlersCallBack.onSuccessFromRemote(brawlersList, response.raw().receivedResponseAtMillis());
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    brawlersCallBack.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerApiResponse> call, @NonNull Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                brawlersCallBack.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}