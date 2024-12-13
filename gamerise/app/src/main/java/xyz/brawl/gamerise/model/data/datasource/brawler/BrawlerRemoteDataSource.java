package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.Brawler;
import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

public class BrawlerRemoteDataSource extends AbstractRemoteDataSource {
    protected BrawlerRemoteDataSource(ApiService apiService) {
        super(apiService);
    }

    /**
     * Recupera tutti brawlers dall'API REST.
     *
     * @return Lista di brawlers.
     */
    public CompletableFuture<ItemsResponse> getBrawlerListAsync() {
        CompletableFuture<ItemsResponse> future = makeGETRequest(apiService.getBrawlerList());

        // Handle the response asynchronously
        return future.thenApply(response -> {
            // Process the response and return the result
            return response != null ? response : new ItemsResponse();  // Return fallback if needed
        }).exceptionally(e -> {
            // Handle exception and return a fallback response
            e.printStackTrace();
            return new ItemsResponse(); // Return fallback on error
        });
    }

    private CompletableFuture<ItemsResponse> makeGETRequest2(Call<ItemsResponse> call) {
        // Create a CompletableFuture to be completed once the request finishes
        CompletableFuture<ItemsResponse> future = new CompletableFuture<>();

        // Asynchronous Retrofit call
        call.enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> c, Response<ItemsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Complete the future with the response body if the request was successful
                    future.complete(response.body());
                } else {
                    // If the response was not successful, complete exceptionally
                    future.completeExceptionally(new Exception("Error: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<ItemsResponse> c, Throwable t) {
                // If the request fails, complete the future exceptionally
                future.completeExceptionally(t);
            }
        });

        // Return the CompletableFuture
        return future;
    }

}
