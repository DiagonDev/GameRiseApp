package xyz.brawl.gamerise.model.data.datasource.brawler;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.model.data.datasource.ApiService;
import xyz.brawl.gamerise.util.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class BrawlerRepository {

    private final ApiService apiService;
    OkHttpClient httpClient;
    // private final MyDao myDao;

    public BrawlerRepository(/* , MyDao myDao */ ) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

        httpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(ApiService.class);
        // this.myDao = myDao;
    }



    public ItemsResponse getBrawlers() {
        BrawlerRemoteDataSource brds = new BrawlerRemoteDataSource(apiService);

        // Secondo: sincronizza i dati da remoto (API)
        // Questo potre bbe essere fatto in background, magari con un Worker
        syncDataFromRemote();

        return brds.getBrawlerList();
    }



    // Salva dati nel database locale e/o su API remota
    public void updateData() {
        //
    }

    // Sincronizza i dati da API remota
    private void syncDataFromRemote() {
        //
    }

    // Salva i dati sull'API remota
    private void updateDataOnRemote() {
        //
    }

    // Cancellazione dei dati
    public void deleteData() {
        //
    }


    public void shutdownClient() {
        // Clean-up logic: shutdown Dispatcher and evict all connections
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();
    }

}

