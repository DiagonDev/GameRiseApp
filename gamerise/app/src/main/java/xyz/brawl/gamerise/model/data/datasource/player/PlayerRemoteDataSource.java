package xyz.brawl.gamerise.model.data.datasource.player;

import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

public class PlayerRemoteDataSource extends AbstractRemoteDataSource {

    protected PlayerRemoteDataSource(ApiService apiService) {
        super(apiService);
    }

    public String getPlayerStats(String tag) {
        return null;
    }
}
