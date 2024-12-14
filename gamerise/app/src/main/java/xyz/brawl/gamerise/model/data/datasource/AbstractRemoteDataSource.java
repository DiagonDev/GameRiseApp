package xyz.brawl.gamerise.model.data.datasource;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.service.ApiService;

public abstract class AbstractRemoteDataSource {
    protected final ApiService apiService;

    @Deprecated
    protected AbstractRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * Esegue una richiesta di rete in modo asincrono.
     *
     * @return La risposta come stringa.
     */

    // Metodo generico per gestire le chiamate Retrofit
    // call = apiService.getBrawlersLeaderboard(countryCode, brawlerId);
    protected <T> CompletableFuture<T> makeGETRequest(Call<T> call) {
        CompletableFuture<T> future = new CompletableFuture<>();

        call.enqueue(new Callback<T>() { // async call
            @Override
            public void onResponse(Call<T> c, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    future.complete(response.body());
                } else {
                    future.completeExceptionally(new Exception("Error: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> c, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

}
