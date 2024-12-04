package xyz.brawl.gamerise.model.data.datasource.player;

import xyz.brawl.gamerise.model.data.api.ApiClient;
import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;

public class PlayerRemoteDataSource extends AbstractRemoteDataSource {

    protected PlayerRemoteDataSource(ApiClient apiClient) {
        super(apiClient);
    }

    public String getPlayerStats(String tag) {
        return threadedNetworkRequest("/players/" + tag);
    }
}
