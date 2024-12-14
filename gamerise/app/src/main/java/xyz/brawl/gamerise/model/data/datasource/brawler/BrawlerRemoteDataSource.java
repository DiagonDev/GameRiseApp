package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.concurrent.CompletableFuture;
import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.service.ApiService;

public class BrawlerRemoteDataSource extends AbstractRemoteDataSource {
    public BrawlerRemoteDataSource(ApiService apiService) {
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
}
