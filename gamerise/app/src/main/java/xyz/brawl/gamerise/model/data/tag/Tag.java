package xyz.brawl.gamerise.model.data.tag;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Classe bivalente: Modello di dominio, Entity di Room
 * Modello di dominio: usata per popolare il recycler view in TagActivity
 * Entity: nel Database Room (visualizzata come Player) è l'entità protagonista
 */
@Entity(tableName = "Player")
public class Tag {

    @PrimaryKey
    @NonNull
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

    public void setTag(@NonNull String tag) {
        this.tag = tag;
    }

    public void setNomeGiocatore(String nomeGiocatore) {
        this.nomeGiocatore = nomeGiocatore;
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
