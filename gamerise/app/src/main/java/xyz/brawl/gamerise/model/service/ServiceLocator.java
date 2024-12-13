package xyz.brawl.gamerise.model.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.util.Constants;

public class ServiceLocator {
    private static volatile ServiceLocator INSTANCE = null;

    public ServiceLocator() {
    }
    public static ServiceLocator getInstance(){
        if (INSTANCE == null){
            synchronized (ServiceLocator.class){
                if (INSTANCE == null){
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }
    public BattleLogApiService getBattleLogApiService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(BattleLogApiService.class);
    }

    /* TODO: implementare data base
    public BattleLogRoomDatabase getBattleLogRoomDatabase(){
        return BattleLogRoomDatabase.getDatabase(context);
    }
    */
}
