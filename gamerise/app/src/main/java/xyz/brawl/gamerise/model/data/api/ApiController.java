package xyz.brawl.gamerise.model.data.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiController {
    private ApiService apiService;

    public ApiController() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sk8.fun:5223/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    // Metodo generico per gestire le chiamate Retrofit
    private void makeRequest(Call<String> call, Callback<String> callback) {
        call.enqueue(callback); // Asincrono, Retrofit gestisce i thread
    }

    // Esempi di chiamate:
    public void getPlayer(String tag, Callback<String> callback) {
        makeRequest(apiService.getPlayer(tag.replace("#", "%23")), callback);
    }

    public void getBattlelog(String tag, Callback<String> callback) {
        makeRequest(apiService.getBattlelog(tag.replace("#", "%23")), callback);
    }

    public void getClubsLeaderboard(String countryCode, Callback<String> callback) {
        makeRequest(apiService.getClubsLeaderboard(countryCode), callback);
    }

}

