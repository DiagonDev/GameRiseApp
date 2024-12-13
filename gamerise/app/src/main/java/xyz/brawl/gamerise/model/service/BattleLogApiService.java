package xyz.brawl.gamerise.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;

public interface BattleLogApiService {
    @GET("players/{tag}/battlelog")
    Call<BattleLogApiResponse> getBattlelog2(@Path("tag") String playerTag);

}
