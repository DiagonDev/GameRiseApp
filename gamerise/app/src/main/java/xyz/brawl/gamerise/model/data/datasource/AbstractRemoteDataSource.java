package xyz.brawl.gamerise.model.data.datasource;

import androidx.annotation.NonNull;

import java.util.concurrent.CompletableFuture;

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
    protected <T> CompletableFuture<T> makeGETRequest(Call<T> call) {
        // Create a CompletableFuture to be completed once the request finishes
        CompletableFuture<T> future = new CompletableFuture<>();

        // Asynchronous Retrofit call
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> c, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Complete the future with the response body if the request was successful
                    future.complete(response.body());
                } else {
                    // If the response was not successful, complete exceptionally
                    future.completeExceptionally(new Exception("Error: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> c, Throwable t) {
                // If the request fails, complete the future exceptionally
                future.completeExceptionally(t);
            }
        });

        // Return the CompletableFuture
        return future;
    }
}
