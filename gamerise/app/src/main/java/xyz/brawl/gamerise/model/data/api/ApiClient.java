package xyz.brawl.gamerise.model.data.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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


        String json;




    }

    // Metodo generico per gestire le chiamate Retrofit
    private void makeRequest(Call<String> call, Callback<String> callback) {
        call.enqueue(callback); // Asincrono, Retrofit gestisce i thread
    }



    //
    public CompletableFuture<String> getPlayer(String tag) {

        CompletableFuture<String> future = new CompletableFuture<>();

        // Make the asynchronous call
        apiService.getPlayer(tag).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Complete the future with the response body
                    future.complete(response.body());
                } else {
                    // Complete the future exceptionally if the response is an error
                    future.completeExceptionally(new Exception("Error: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Complete the future exceptionally if the request fails
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    public void getBattlelog(String tag, Callback<String> callback) {
        makeRequest(apiService.getBattlelog(tag.replace("#", "%23")), callback);
    }

    public void getClubsLeaderboard(String countryCode, Callback<String> callback) {
        makeRequest(apiService.getClubsLeaderboard(countryCode), callback);
    }

    public void getBrawlersLeaderboard(String countryCode, int brawlerId, Callback<String> callback) {
        makeRequest(apiService.getBrawlersLeaderboard(countryCode, brawlerId), callback);
    }

    public void getPlayersLeaderboard(String countryCode, Callback<String> callback) {
        makeRequest(apiService.getPlayersLeaderboard(countryCode), callback);
    }

    public void getClubMembers(String clubTag, Callback<String> callback) {
        makeRequest(apiService.getClubMembers(clubTag), callback);
    }

    public void getClub(String clubTag, Callback<String> callback) {
        makeRequest(apiService.getClub(clubTag), callback);
    }

    public void getBrawlerList(Callback<String> callback) {
        makeRequest(apiService.getBrawlerList(), callback);
    }

    public void getBrawler(int brawlerId, Callback <String> callback) {
        makeRequest(apiService.getBrawler(brawlerId), callback);
    }

    public void getEvents(Callback<String> callback) {
        makeRequest(apiService.getEvents(), callback);
    }
}

