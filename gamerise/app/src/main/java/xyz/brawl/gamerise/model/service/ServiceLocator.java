package xyz.brawl.gamerise.model.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    public StatsApiService getStatsApiService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(StatsApiService.class);
    }

    /* TODO: implement data base
    public RoomDatabase getDAO(){}
    */
}
