package xyz.brawl.gamerise.model.data.tag;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//TODO: cambiare tableName into Player
@Entity (tableName = "Tag")
public class Tag {
    @PrimaryKey @NonNull
    String tag;

    String nomeGiocatore;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public Tag(String nomeGiocatore, String tag) {
        this.nomeGiocatore = nomeGiocatore;
        this.tag = tag;
        this.timestamp = System.currentTimeMillis();
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public String getTag() {
        return tag;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
