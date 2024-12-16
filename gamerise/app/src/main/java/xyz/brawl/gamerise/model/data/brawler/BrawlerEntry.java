package xyz.brawl.gamerise.model.data.brawler;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Model class for BattleLogEntry
public class BrawlerEntry {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("gadgets")
    private List<GadgetEntry> gadgets;

    @SerializedName("starPowers")
    private List<StarPowerEntry> starPowers;

    private int brawlerPin;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<GadgetEntry> getGadgets() {
        return gadgets;
    }

    public List<StarPowerEntry> getStarPowers() {
        return starPowers;
    }

    public int getBrawlerPin() {
        return brawlerPin;
    }

}
