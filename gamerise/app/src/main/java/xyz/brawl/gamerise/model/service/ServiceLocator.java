package xyz.brawl.gamerise.model.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.util.Constants;

public class ServiceLocator {
    private static volatile ServiceLocator INSTANCE = null;

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
                /// Secondo if se ho più thread
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }
    public ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    /* TODO: implementare data base
    public RoomDatabase getDAO(){}
    */
}
