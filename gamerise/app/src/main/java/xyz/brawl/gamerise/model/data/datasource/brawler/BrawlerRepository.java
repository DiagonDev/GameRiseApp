package xyz.brawl.gamerise.model.data.datasource.brawler;

import xyz.brawl.gamerise.model.data.datasource.ApiService;

import androidx.lifecycle.LiveData;       // Per gestire i dati reattivi
import androidx.room.*;             // Per la cancellazione dei dati

public class BrawlerRepository {
    private final ApiService apiService;
    // private final MyDao myDao;

    public BrawlerRepository(ApiService apiService /* , MyDao myDao */ ) {
        this.apiService = apiService;
        // this.myDao = myDao;
    }

    // Recupera dati, preferendo la fonte locale, se disponibile
    public LiveData<List<Data>> getData() {
        // Primo: prova a ottenere i dati dal database locale
        LiveData<List<Data>> localData = myDao.getAllData();

        // Secondo: sincronizza i dati da remoto (API)
        // Questo potrebbe essere fatto in background, magari con un Worker
        syncDataFromRemote();

        return localData;
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

