package xyz.brawl.gamerise.model.data.stat.api;

import com.google.gson.annotations.SerializedName;

public class StatsApiResponse {
    @SerializedName("3vs3Victories")
    private int _3vs3Victories;
    private int trophies;
    private int expLevel;
    private ClubEntry club;
    private int highestTrophies;
    private int rank;
    private int soloVictories;
    private int duoVictories;

    public ClubEntry getClub() { return club; }

    public int get3vs3Victories() { return _3vs3Victories; }

    public int getTrophies() { return trophies; }
    public void setTrophies(int trophies) { this.trophies = trophies; }

    public int getExpLevel() { return expLevel; }

    public int getHighestTrophies() { return highestTrophies; }

    public int getRank() { return rank; }

    public int getSoloVictories() { return soloVictories; }

    public int getDuoVictories() { return duoVictories; }
}
