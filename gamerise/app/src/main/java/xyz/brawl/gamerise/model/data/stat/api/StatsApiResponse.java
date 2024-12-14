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
    public void setClub(ClubEntry club) { this.club = club; }

    public int get3vs3Victories() { return _3vs3Victories; }
    public void set3vs3Victories(int _3vs3Victories) { this._3vs3Victories = _3vs3Victories; }

    public int getTrophies() { return trophies; }
    public void setTrophies(int trophies) { this.trophies = trophies; }

    public int getExpLevel() { return expLevel; }
    public void setExpLevel(int expLevel){ this.expLevel = expLevel; }

    public int getHighestTrophies() { return highestTrophies; }
    public void setHighestTrophies(int highestTrophies) { this.highestTrophies = highestTrophies; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public int getSoloVictories() { return soloVictories; }
    public void setSoloVictories(int soloVictories) { this.soloVictories = soloVictories; }

    public int getDuoVictories() { return duoVictories; }
    public void setDuoVictories(int duoVictories) { this.duoVictories = duoVictories; }
}
