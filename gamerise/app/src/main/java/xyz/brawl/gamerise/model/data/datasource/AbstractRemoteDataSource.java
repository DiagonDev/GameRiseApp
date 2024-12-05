package xyz.brawl.gamerise.model.data.datasource;

import com.google.gson.Gson;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AbstractRemoteDataSource {
    protected final ApiService apiService;

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
    protected CompletableFuture<Gson> makeGETRequest(Call<String> call) {
        CompletableFuture<String> future = new CompletableFuture<>();

        call.enqueue(new Callback<String>() { // async call
            @Override
            public void onResponse(Call<String> c, Response<String> response) {
                if (response.isSuccessful() && response.body() != null)
                    future.complete(response.body());
                else future.completeExceptionally(new Exception("Error: " + response.code()));
            }

            @Override
            public void onFailure(Call<String> c, Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future;
    }
}
