package xyz.brawl.gamerise.model.service;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.datasource.player.PlayerRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.player.PlayerLocalDataSource;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.util.Constants;
import xyz.brawl.gamerise.util.JSONParserUtils;

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

    public GameRiseDatabase getDatabase(Application application) {
        return GameRiseDatabase.getDatabase(application);
    }
    /*
    public PlayerRepository getPlayerRepository(Application application, boolean debugMode) {
        BasePlayerRemoteDataSource playerRemoteDataSource;
        PlayerLocalDataSource playerLocalDataSource;
        if (debugMode) {
            JSONParserUtils jsonParserUtils = new JSONParserUtils(application);
            playerRemoteDataSource = new PlayerRemoteDataSource(application, jsonParserUtils);

        }
        playerLocalDataSource = new PlayerLocalDataSource(application);
    }
    */
}
