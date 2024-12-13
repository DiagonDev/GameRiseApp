package xyz.brawl.gamerise.model.repository;

import retrofit2.Retrofit;
import xyz.brawl.gamerise.model.data.datasource.ApiService;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.brawl.gamerise.model.data.datasource.brawler.BrawlerRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.ItemsResponse;
import xyz.brawl.gamerise.util.Constants;


public class BrawlerRepository {

    private final ApiService apiService;
    // private final MyDao myDao;

    public BrawlerRepository(/* , MyDao myDao */ ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        // this.myDao = myDao;
    }


/*
    public ItemsResponse getBrawlers() {
        BrawlerRemoteDataSource brds = new BrawlerRemoteDataSource(apiService);

        // Secondo: sincronizza i dati da remoto (API)
        // Questo potre bbe essere fatto in background, magari con un Worker
        syncDataFromRemote();

        return brds.getBrawlerList();
    }

*/

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

}

