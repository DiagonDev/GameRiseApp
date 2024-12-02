package xyz.brawl.gamerise.model.data.datasource.battle;

import android.accounts.NetworkErrorException;

import xyz.brawl.gamerise.model.data.api.ApiClient;
import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;

/**
 * This class represents the remote data source for the Battle entity.
 * remote because uses the data from the API REST
 */
public class BattleRemoteDataSource extends AbstractRemoteDataSource {

    public BattleRemoteDataSource(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Recupera tutte le battaglie dall'API REST.
     *
     * @return Lista di battaglie.
     * @throws NetworkErrorException in caso di errore di rete o server.
     */
    public String getBattlelog(String playerTag) throws NetworkErrorException {
        return threadedNetworkRequest("/player/%23" + playerTag + "/battlelog");
    }
}
