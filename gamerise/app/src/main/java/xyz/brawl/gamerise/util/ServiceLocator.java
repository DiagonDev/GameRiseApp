package xyz.brawl.gamerise.util;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.battle.BattleLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BrawlerRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BrawlersLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.starPower.BaseStarPowerLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.starPower.StarPowerLocalDataSource;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.model.service.ApiService;

public class ServiceLocator {

    private static volatile ServiceLocator INSTANCE = null;

    private ServiceLocator() {}

    /**
     * Returns an instance of ServiceLocator class.
     * @return An instance of ServiceLocator.
     */
    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized(ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                        .build();
                return chain.proceed(request);
            })
            .build();

    /**
     * Returns an instance of NewsApiService class using Retrofit.
     * @return an instance of NewsApiService.
     */
    //TODO: leo
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

    /**
     * Returns an instance of NewsRoomDatabase class to manage Room database.
     * @param application Param for accessing the global application state.
     * @return An instance of NewsRoomDatabase.
     */
    public GameRiseDatabase getDatabase(Application application) {
        return GameRiseDatabase.getDatabase(application);
    }

    /**
     * Returns an instance of INewsRepositoryWithLiveData.
     * @param application Param for accessing the global application state.
     * @param debugMode Param to establish if the application is run in debug mode.
     * @return An instance of INewsRepositoryWithLiveData.
     */
    public BattleLogRepository getBattleLogRepository(Application application, boolean debugMode) {
        //BaseBattleLogRemoteDataSource newsRemoteDataSource;
        BaseBattleLocalDataSource battleLocalDataSource;

        //TODO: leo
        /*if (debugMode) {
            JSONParserUtils jsonParserUtil = new JSONParserUtils(application);
            newsRemoteDataSource =
                    new ArticleMockDataSource(jsonParserUtil);
        } else {
            newsRemoteDataSource =
                    new ArticleRemoteDataSource(application.getString(R.string.news_api_key));
        }*/

        battleLocalDataSource = new BattleLocalDataSource(getDatabase(application));

        return new BattleLogRepository(battleLocalDataSource);
    }

    public BrawlersRepository getBrawlersRepository(Application application, boolean debugMode) {
        BaseBrawlersLocalDataSource brawlersLocalDataSource;
        BaseBrawlersRemoteDataSource brawlerRemoteDataSource;
        brawlerRemoteDataSource = new BrawlerRemoteDataSource(getApiService(), application.callbac);
        brawlersLocalDataSource = new BrawlersLocalDataSource(getDatabase(application));
        return new BrawlersRepository(brawlersLocalDataSource, brawlerRemoteDataSource);
    }

    public StarPowerRepository getStarPowerRepository(Application application, boolean debugMode) {
        BaseStarPowerLocalDataSource starPowerLocalDataSource;
        //TODO: leo
        starPowerLocalDataSource = new StarPowerLocalDataSource(getDatabase(application));

        return new StarPowerRepository(starPowerLocalDataSource);
    }
}
