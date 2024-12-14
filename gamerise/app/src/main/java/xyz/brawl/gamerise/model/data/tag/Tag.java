package xyz.brawl.gamerise.model.data.tag;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Tag")
public class Tag {
    @PrimaryKey @NonNull
    String tag;

    String nomeGiocatore;

    public Tag(String nomeGiocatore, String tag) {
        this.nomeGiocatore = nomeGiocatore;
        this.tag = tag;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public String getTag() {
        return tag;
    }
}
