package xyz.brawl.gamerise.model.data.datasource.brawler;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


import java.util.concurrent.CompletableFuture;

import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.model.data.datasource.ApiService;


public class BrawlerRepository {
    private String baseAPIUrl = "https://sk8.fun:5223/";

    private final ApiService apiService;
    OkHttpClient httpClient;

    public BrawlerRepository() {

        httpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseAPIUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public CompletableFuture<ItemsResponse> getBrawlers() {
        BrawlerRemoteDataSource brds = new BrawlerRemoteDataSource(apiService);

        CompletableFuture<ItemsResponse> future = brds.getBrawlerListAsync();

        // Process the result asynchronously
        return future.thenApply(response -> {
            System.out.println("CompletableFuture completed");
            return response;
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });

    }

    public void shutdownClient() {
        // Clean-up logic: shutdown Dispatcher and evict all connections
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }


}

