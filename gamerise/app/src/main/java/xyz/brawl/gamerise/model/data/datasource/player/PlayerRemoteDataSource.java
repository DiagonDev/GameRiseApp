package xyz.brawl.gamerise.model.data.datasource.player;

import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.ApiService;

public class PlayerRemoteDataSource extends AbstractRemoteDataSource {
    private final ApiService apiService;

    protected PlayerRemoteDataSource(ApiService apiService) {
        super();
        this.apiService = apiService;
    }

    public String getPlayerStats(String tag) {
        return null;
    }
}
