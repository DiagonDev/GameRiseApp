package xyz.brawl.gamerise.util;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.source.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.source.battle.BaseBattleRemoteDataSource;
import xyz.brawl.gamerise.source.battle.BattleLocalDataSource;
import xyz.brawl.gamerise.source.battle.BattleRemoteDataSource;
import xyz.brawl.gamerise.source.brawler.BaseBrawlersLocalDataSource;
import xyz.brawl.gamerise.source.brawler.BaseBrawlersRemoteDataSource;
import xyz.brawl.gamerise.source.brawler.BrawlerRemoteDataSource;
import xyz.brawl.gamerise.source.brawler.BrawlersLocalDataSource;
import xyz.brawl.gamerise.source.gadget.BaseGadgetLocalDataSource;
import xyz.brawl.gamerise.source.gadget.BaseGadgetRemoteDataSource;
import xyz.brawl.gamerise.source.gadget.GadgetLocalDataSource;
import xyz.brawl.gamerise.source.gadget.GadgetRemoteDataSource;
import xyz.brawl.gamerise.source.player.BasePlayerRemoteDataSource;
import xyz.brawl.gamerise.source.player.PlayerRemoteDataSource;
import xyz.brawl.gamerise.source.starPower.BaseStarPowerLocalDataSource;
import xyz.brawl.gamerise.source.starPower.BaseStarPowerRemoteDataSource;
import xyz.brawl.gamerise.source.starPower.StarPowerLocalDataSource;
import xyz.brawl.gamerise.source.starPower.StarPowerRemoteDataSource;
import xyz.brawl.gamerise.source.stats.BaseStatsLocalDataSource;
import xyz.brawl.gamerise.source.stats.BaseStatsRemoteDataSource;
import xyz.brawl.gamerise.source.stats.StatsLocalDataSource;
import xyz.brawl.gamerise.source.stats.StatsRemoteDataSource;
import xyz.brawl.gamerise.source.tag.BaseTagLocalDataSource;
import xyz.brawl.gamerise.source.tag.TagLocalDataSource;
import xyz.brawl.gamerise.source.user.BaseUserAuthenticationRemoteDataSource;
import xyz.brawl.gamerise.source.user.BaseUserTagRemoteDataSource;
import xyz.brawl.gamerise.source.user.UserAuthenticationFirebaseDataSource;
import xyz.brawl.gamerise.source.user.UserFirebaseDataSource;
import xyz.brawl.gamerise.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.repository.player.PlayerRepository;
import xyz.brawl.gamerise.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.repository.stats.StatsRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;
import xyz.brawl.gamerise.repository.user.UserRepository;
import xyz.brawl.gamerise.service.ApiService;

public class ServiceLocator {

    private static volatile ServiceLocator INSTANCE = null;

    private ServiceLocator() {
    }

    /**
     * Returns an instance of ServiceLocator class.
     *
     * @return An instance of ServiceLocator.
     */
    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceLocator.class) {
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
     *
     * @return an instance of NewsApiService.
     */
    //TODO: leo
    public ApiService getApiService() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
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
     *
     * @param application Param for accessing the global application state.
     * @return An instance of NewsRoomDatabase.
     */
    public GameRiseDatabase getDatabase(Application application) {
        return GameRiseDatabase.getDatabase(application);
    }

    /**
     * Returns an instance of INewsRepositoryWithLiveData.
     *
     * @param application Param for accessing the global application state.
     * @param debugMode   Param to establish if the application is run in debug mode.
     * @return An instance of INewsRepositoryWithLiveData.
     */
    public BattleLogRepository getBattleLogRepository(Application application, boolean debugMode) {
        BaseBattleRemoteDataSource battleRemoteDataSource;
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
        battleRemoteDataSource = new BattleRemoteDataSource(getApiService());
        return new BattleLogRepository(battleLocalDataSource, battleRemoteDataSource);
    }

    public StatsRepository getStatsRepository(Application application, boolean debugMode) {
        BaseStatsRemoteDataSource statsRemoteDataSource;
        BaseStatsLocalDataSource statsLocalDataSource;

        //TODO: leo
        /*if (debugMode) {
            JSONParserUtils jsonParserUtil = new JSONParserUtils(application);
            newsRemoteDataSource =
                    new ArticleMockDataSource(jsonParserUtil);
        } else {
            newsRemoteDataSource =
                    new ArticleRemoteDataSource(application.getString(R.string.news_api_key));
        }*/

        statsLocalDataSource = new StatsLocalDataSource(getDatabase(application));
        statsRemoteDataSource = new StatsRemoteDataSource(getApiService());
        return new StatsRepository(statsLocalDataSource, statsRemoteDataSource);
    }

    public BrawlersRepository getBrawlersRepository(Application application, boolean debugMode) {
        BaseBrawlersLocalDataSource brawlersLocalDataSource;
        BaseBrawlersRemoteDataSource brawlerRemoteDataSource;
        brawlerRemoteDataSource = new BrawlerRemoteDataSource(getApiService());
        brawlersLocalDataSource = new BrawlersLocalDataSource(getDatabase(application));
        return new BrawlersRepository(brawlersLocalDataSource, brawlerRemoteDataSource);
    }

    public TagRepository getTagRepository(Application application, boolean debugMode) {
        BaseTagLocalDataSource tagLocalDataSource;
        //TODO: leo
        tagLocalDataSource = new TagLocalDataSource(getDatabase(application));

        return new TagRepository(tagLocalDataSource);
    }

    public StarPowerRepository getStarPowerRepository(Application application, boolean debugMode) {
        BaseStarPowerLocalDataSource starPowerLocalDataSource;
        BaseStarPowerRemoteDataSource starPowerRemoteDataSource;
        starPowerLocalDataSource = new StarPowerLocalDataSource(getDatabase(application));
        starPowerRemoteDataSource = new StarPowerRemoteDataSource(getApiService());
        return new StarPowerRepository(starPowerLocalDataSource, starPowerRemoteDataSource);
    }

    public GadgetRepository getGadgetRepository(Application application, boolean debugMode) {
        BaseGadgetLocalDataSource gadgetLocalDataSource;
        BaseGadgetRemoteDataSource gadgetRemoteDataSource;
        gadgetLocalDataSource = new GadgetLocalDataSource(getDatabase(application));
        gadgetRemoteDataSource = new GadgetRemoteDataSource(getApiService());
        return new GadgetRepository(gadgetLocalDataSource, gadgetRemoteDataSource);
    }

    public PlayerRepository getPlayerRepository(Application application, boolean debugMode) {
        BasePlayerRemoteDataSource playerRemoteDataSource;
        playerRemoteDataSource = new PlayerRemoteDataSource(getApiService());
        TagRepository tagRepository = getTagRepository(application, debugMode);
        BrawlersRepository brawlersRepository = getBrawlersRepository(application, debugMode);
        StatsRepository statsRepository = getStatsRepository(application, debugMode);
        StarPowerRepository starPowerRepository = getStarPowerRepository(application, debugMode);
        GadgetRepository gadgetRepository = getGadgetRepository(application, debugMode);

        return new PlayerRepository(playerRemoteDataSource, tagRepository, brawlersRepository,
                statsRepository, starPowerRepository, gadgetRepository);
    }

    public UserRepository getUserRepository() {
        BaseUserAuthenticationRemoteDataSource userRemoteAuthenticationDataSource =
                new UserAuthenticationFirebaseDataSource();

        BaseUserTagRemoteDataSource userTagRemoteDataSource =
                new UserFirebaseDataSource();
        return new UserRepository(userRemoteAuthenticationDataSource,
                userTagRemoteDataSource);
    }
}
