package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

/**
 * This class represents the remote data source for the Battle entity.
 * remote because uses the data from the API REST
 */
public class BattleRemoteDataSource extends AbstractRemoteDataSource {
    public BattleRemoteDataSource(ApiService apiService) {
        super(apiService);
    }

    /**
     * Recupera tutte le battaglie dall'API REST.
     *
     * @return Lista di battaglie.
     */
    public String getBattlelog(String playerTag) {
        CompletableFuture<String> future = super.makeGETRequest(apiService.getBattlelog(playerTag));
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
