package xyz.brawl.gamerise.model.data.datasource.brawler;

import retrofit2.Retrofit;
import xyz.brawl.gamerise.model.data.brawler.Brawler;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

import androidx.lifecycle.LiveData;       // Per gestire i dati reattivi
import androidx.room.*;             // Per la cancellazione dei dati

import com.google.gson.*;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrawlerRepository {
    private String baseAPIUrl = "https://sk8.fun:5223/";

    private final ApiService apiService;
    // private final MyDao myDao;

    public BrawlerRepository(ApiService apiService /* , MyDao myDao */ ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sk8.fun:5223/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        this.apiService = apiService;
        // this.myDao = myDao;
    }

    public LiveData<List<Brawler>> getBrawlers() {
        Gson json;
        if (/* i dati sono sul db locale*/ false) {
            // returnali dal db
        } else {
            BrawlerRemoteDataSource remote = new BrawlerRemoteDataSource(apiService);
            json = remote.getBrawlerList();

        }
        return brawlersLiveData;
    }

    // Salva dati nel database locale e/o su API remota
    public void updateData(Data data) {
        // Salva localmente
        myDao.insertData(data);

        // E sincronizza con la fonte remota
        updateDataOnRemote(data);
    }

    // Sincronizza i dati da API remota
    private void syncDataFromRemote() {
        apiService.getData()
                .enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        if (response.isSuccessful()) {
                            // Salva nella fonte locale
                            myDao.insertData(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {
                        // Gestisci errore
                    }
                });
    }

    // Salva i dati sull'API remota
    private void updateDataOnRemote(Data data) {
        apiService.updateData(data)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Gestisci successo
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Gestisci errore
                    }
                });
    }

    // Cancellazione dei dati
    public void deleteData(Data data) {
        myDao.deleteData(data);
        apiService.deleteData(data.getId());
    }

}

