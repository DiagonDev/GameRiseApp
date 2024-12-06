package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import xyz.brawl.gamerise.model.data.brawler.Brawler;
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
    public ItemsResponse getBrawlerList() {
        CompletableFuture<ItemsResponse> future = super.makeGETRequest(apiService.getBrawlerList());

        return future.join();
    }

    /**
     * Recupera un brawler specifico dall'API REST.
     *
     * @return Brawler specifico.
     */
    public List<Brawler> getBrawler(int brawlerId) {
        CompletableFuture<List<Brawler>> future = super.makeGETRequest(apiService.getBrawler(brawlerId));
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
