package xyz.brawl.gamerise.model.data.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("players/{tag}")
    Call<String> getPlayer(@Path("tag") String tag);

    @GET("players/{tag}/battlelog")
    Call<String> getBattlelog(@Path("tag") String playerTag);

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
    Call<String> getBrawlerList();

    @GET("brawlers/{brawlerId}")
    Call<String> getBrawler(@Path("brawlerId") int brawlerId);

    @GET("events/rotation")
    Call<String> getEvents();
}
