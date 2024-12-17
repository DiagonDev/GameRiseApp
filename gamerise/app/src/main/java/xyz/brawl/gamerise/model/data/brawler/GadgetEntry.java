package xyz.brawl.gamerise.model.data.brawler;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = StarPowerEntry.class,
                        parentColumns = "id",
                        childColumns = "brawlerId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class GadgetEntry {
    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    private long brawlerId;

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

    public long getBrawlerId() {
        return brawlerId;
    }

    public void setBrawlerId(long brawlerId) {
        this.brawlerId = brawlerId;
    }
}