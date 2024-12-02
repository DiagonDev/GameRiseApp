package xyz.brawl.gamerise.model.data.api;

import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private final ApiService apiService;
    public ApiClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);
    }

    // Metodo generico per gestire le chiamate Retrofit
    // call = apiService.getBrawlersLeaderboard(countryCode, brawlerId);
    private CompletableFuture<String> GETRequest(Call<String> call) {
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

