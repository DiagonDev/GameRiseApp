package xyz.brawl.gamerise.model.data.brawler;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Model class for BattleLogEntry
//TODO: gestire Database per gadgetEntries e starPowersEntries
@Entity
public class BrawlerEntry {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("gadgetEntries")
    private List<GadgetEntry> gadgetEntries;

    @SerializedName("starPowersEntries")
    private List<StarPowerEntry> starPowersEntries;

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

    public void setName(String name) {
        this.name = name;
    }

    public List<GadgetEntry> getGadgetEntries() {
        return gadgetEntries;
    }

    public void setGadgetEntries(List<GadgetEntry> gadgetEntries) {
        this.gadgetEntries = gadgetEntries;
    }

    public List<StarPowerEntry> getStarPowersEntries() {
        return starPowersEntries;
    }

    public void setStarPowersEntries(List<StarPowerEntry> starPowersEntries) {
        this.starPowersEntries = starPowersEntries;
    }

    public int getBrawlerPin() {
        return brawlerPin;
    }

    public void setBrawlerPin(int brawlerPin) {
        this.brawlerPin = brawlerPin;
    }

}
