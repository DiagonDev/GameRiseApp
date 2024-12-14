package xyz.brawl.gamerise.model.repository;

import android.content.Context;

import retrofit2.Retrofit;
import xyz.brawl.gamerise.model.service.ApiService;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.model.data.datasource.brawler.BrawlerRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.ItemsResponse;
import xyz.brawl.gamerise.model.service.ServiceLocator;
import xyz.brawl.gamerise.util.Constants;
import xyz.brawl.gamerise.util.ResponseCallback;


public class BrawlerRepository {

    private final ApiService apiService;

    private Context context;
    private ResponseCallback responseCallback;

    public BrawlerRepository(Context context, ResponseCallback responseCallback) {
        this.apiService = ServiceLocator.getInstance().getApiService();
        this.context = context;
        this.responseCallback = responseCallback;
    }

    public BrawlerRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public ItemsResponse getBrawlers() {
        BrawlerRemoteDataSource brds = new BrawlerRemoteDataSource(apiService);

        return brds.getBrawlerList();
    }

}

