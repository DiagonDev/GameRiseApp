package xyz.brawl.gamerise.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;

public interface StatsApiService {
    @GET("players/{tag}")
    Call<StatsApiResponse> getPlayer2(@Path("tag") String tag);

}
