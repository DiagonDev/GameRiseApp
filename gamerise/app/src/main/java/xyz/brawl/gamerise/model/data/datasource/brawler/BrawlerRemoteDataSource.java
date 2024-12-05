package xyz.brawl.gamerise.model.data.datasource.brawler;

import com.google.gson.Gson;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

public class BrawlerRemoteDataSource extends AbstractRemoteDataSource {
    protected BrawlerRemoteDataSource(ApiService apiService) {
        super(apiService);
    }

    /**
     * Recupera tutti brawlers dall'API REST.
     *
     * @return Lista di brawlers.
     */
    public String getBrawlerList() {
        CompletableFuture<Gson> future = super.makeGETRequest(apiService.getBrawlerList());
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera un brawler specifico dall'API REST.
     *
     * @return Brawler specifico.
     */
    public String getBrawler(int brawlerId) {
        CompletableFuture<String> future = super.makeGETRequest(apiService.getBrawler(brawlerId));
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
