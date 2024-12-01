package xyz.brawl.gamerise.model.data.battle.datasource;

import java.util.List;

import xyz.brawl.gamerise.model.data.api.ApiClient;
import xyz.brawl.gamerise.model.data.battle.Battle;

/**
 * This class represents the remote data source for the Battle entity.
 * remote because uses the data from the API REST
 */
public class BattleRemoteDataSource {
    private final ApiClient apiClient;

    public BattleRemoteDataSource(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
    /**
     * Recupera tutte le battaglie dall'API REST.
     * @return Lista di battaglie.
     * @throws Exception in caso di errore di rete o server.
     */
    public List<Battle> getBattlesFromApi() throws Exception {
        return apiController.get
    }
}
