package xyz.brawl.gamerise.model.data.datasource.stats;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.BattleMapper;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.service.ApiService;

public class StatsRemoteDataSource extends BaseStatsRemoteDataSource{
    private final ApiService apiService;

    public StatsRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getStats(String tagId) {
       //da fare da leo
    }
}


