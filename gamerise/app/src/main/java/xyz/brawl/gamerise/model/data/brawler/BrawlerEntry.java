package xyz.brawl.gamerise.model.data.brawler;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;


/**
 * Classe trivalente: Modello di dominio, Entity di Room e Entry di Retrofit
 * Modello di dominio: usata per popolare gridView BrawlerFragment e BrawlerDetailFragment
 * Entity: nel Database Room, in relazione uno a molti con gadgetEntries e starPowersEntries
 * Entry: nel parsing Json con Retrofit per la chiamata API di player/{tag}
 */
@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "tag", // Colonna primaria della tabella Tag
                        childColumns = "tagId", // Colonna in OwnsEntity che rappresenta il riferimento a Tag
                        onDelete = ForeignKey.CASCADE // Comportamento in caso di eliminazione del Tag
                )
        },
        indices = {@androidx.room.Index(value = "brawlerId")}
)
public class BrawlerEntry {

    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;
    public String tagId;
    private int brawlerPin;
    private int power;
    private int rank;


    @Ignore     // Non viene inserito nel database, ma viene comunque gestito da Retrofit
    @SerializedName("gadgets")
    private List<GadgetEntry> gadgetEntries;

    @Ignore     // Non viene inserito nel database, ma viene comunque gestito da Retrofit
    @SerializedName("starPowers")
    private List<StarPowerEntry> starPowersEntries;


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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
