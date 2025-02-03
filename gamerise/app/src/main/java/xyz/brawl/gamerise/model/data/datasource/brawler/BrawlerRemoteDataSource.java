package xyz.brawl.gamerise.model.data.datasource.brawler;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.BrawlerListResponse;
import xyz.brawl.gamerise.model.service.ApiService;
import xyz.brawl.gamerise.util.ResponseCallback;

public class BrawlerRemoteDataSource extends BaseBrawlersRemoteDataSource {
    private final ApiService apiService;

    public BrawlerRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getBrawler(int brawlerId) {
        /*apiService.getBrawler(brawlerId).enqueue(new Callback<BrawlerEntry>() {
            @Override
            public void onResponse(Call<BrawlerEntry> call, Response<BrawlerEntry> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BrawlerEntry> brawlersList = response.body();
                    // Passa il risultato e il tempo di aggiornamento al callback
                    brawlersCallBack.onSuccessFromRemote(response.body(), response.raw().receivedResponseAtMillis());
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    brawlersCallBack.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(Call<BrawlerEntry> call, Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                brawlersCallBack.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });*/
    }

    @Override
    public void getBrawlerList() {
        apiService.getBrawlerList().enqueue(new Callback<BrawlerListResponse>() {
            @Override
            public void onResponse(Call<BrawlerListResponse> call, Response<BrawlerListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BrawlerEntry> brawlersList = response.body().getItems();
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
            public void onFailure(Call<BrawlerListResponse> call, Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                brawlersCallBack.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
