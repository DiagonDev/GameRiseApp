package xyz.brawl.gamerise.model.data.datasource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import xyz.brawl.gamerise.model.data.api.ApiClient;

public abstract class AbstractRemoteDataSource {
    private final ApiClient apiClient;
    private final ExecutorService executorService;

    protected AbstractRemoteDataSource(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Esegue una richiesta di rete in modo asincrono.
     *
     * @param endpoint L'endpoint dell'API.
     * @return La risposta come stringa.
     */
    public String threadedNetworkRequest(String endpoint) {
        String result = null;
        return result;
    }

    /**
     * Chiama questo metodo per rilasciare le risorse del thread pool.
     */
    public void shutdown() {
        executorService.shutdown();
    }
}
