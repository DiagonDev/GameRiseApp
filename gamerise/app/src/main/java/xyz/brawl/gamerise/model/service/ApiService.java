package xyz.brawl.gamerise.model.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.BrawlerListResponse;
import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;

public interface ApiService {

    @GET("players/{tag}")
    Call<StatsApiResponse> getPlayer(@Path("tag") String tag);

    @GET("players/{tag}/battlelog")
    Call<BattleLogApiResponse> getBattlelog(@Path("tag") String playerTag);

    @GET("rankings/{countryCode}/clubs")
    Call<String> getClubsLeaderboard(@Path("countryCode") String countryCode);

    @GET("rankings/{countryCode}/brawlers/{brawlerId}")
    Call<String> getBrawlersLeaderboard(@Path("countryCode") String countryCode, @Path("brawlerId") int brawlerId);

    @GET("rankings/{countryCode}/players")
    Call<String> getPlayersLeaderboard(@Path("countryCode") String countryCode);

    @GET("clubs/{clubTag}/members")
    Call<String> getClubMembers(@Path("clubTag") String clubTag);

    @GET("clubs/{clubTag}")
    Call<String> getClub(@Path("clubTag") String clubTag);

    @GET("brawlers")
    Call<BrawlerListResponse> getBrawlerList();

    @GET("brawlers/{brawlerId}")
    Call<BrawlerEntry> getBrawler(@Path("brawlerId") int brawlerId);

    @GET("events/rotation")
    Call<String> getEvents();
}
