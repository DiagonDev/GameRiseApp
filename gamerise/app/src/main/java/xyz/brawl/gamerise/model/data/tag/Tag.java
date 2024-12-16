package xyz.brawl.gamerise.model.data.tag;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import xyz.brawl.gamerise.model.data.battle.Battle;

@Entity(tableName = "Player",
        foreignKeys = {
                @ForeignKey(
                        entity = Battle.class,
                        parentColumns = "battleId",
                        childColumns = "battleId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Tag {
    @PrimaryKey @NonNull
    String tag;

    String nomeGiocatore;

    int battleId;

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

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
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
