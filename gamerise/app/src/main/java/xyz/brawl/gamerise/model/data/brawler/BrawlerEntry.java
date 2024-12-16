package xyz.brawl.gamerise.model.data.brawler;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;

/**
 *
 */
@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "id",
                        childColumns = "gadgetId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = BrawlerEntry.class,
                        parentColumns = "id",
                        childColumns = "starPowerId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class BrawlerEntry {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    private int gadgetId;
    private int starPowerId;


    @Ignore     // Non viene inserito nel database, ma viene comunque gestito da Retrofit
    @SerializedName("gadgetEntries")
    private List<GadgetEntry> gadgetEntries;

    @Ignore     // Non viene inserito nel database, ma viene comunque gestito da Retrofit
    @SerializedName("starPowersEntries")
    private List<StarPowerEntry> starPowersEntries;

    private int brawlerPin;

    public BrawlerEntry(){
        // Costruttore vuoto necessario per Room
    }


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

    public int getGadgetId() {
        return gadgetId;
    }

    public void setGadgetId(int gadgetId) {
        this.gadgetId = gadgetId;
    }

    public int getStarPowerId() {
        return starPowerId;
    }

    public void setStarPowerId(int starPowerId) {
        this.starPowerId = starPowerId;
    }
}
