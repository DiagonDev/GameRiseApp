package xyz.brawl.gamerise.model.data.player;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;

public class PlayerApiResponse {
    public String tag;
    @SerializedName("3vs3Victories") // annotation di Retrofit per il parsing gson
    public int _3vs3Victories;
    public int trophies;
    public int expLevel;
    public ClubEntry club;
    public int highestTrophies;
    public int rank;
    public int soloVictories;
    public int duoVictories;
    public List<BrawlerEntry> brawlers;
    public String name;

}
