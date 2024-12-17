package xyz.brawl.gamerise.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.tag.Tag;

//aggiunta delle chiavi esterne alla tabella OwnsEntityper Tag e brawlerEnity
@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Tag.class,
                        parentColumns = "tag", // Colonna primaria della tabella Tag
                        childColumns = "tagId", // Colonna in OwnsEntity che rappresenta il riferimento a Tag
                        onDelete = ForeignKey.CASCADE // Comportamento in caso di eliminazione del Tag
                ),
                @ForeignKey(
                        entity = BrawlerEntry.class,
                        parentColumns = "id", // Colonna primaria della tabella BrawlerEntry
                        childColumns = "brawlerId", // Colonna in OwnsEntity che rappresenta il riferimento a BrawlerEntry
                        onDelete = ForeignKey.CASCADE // Comportamento in caso di eliminazione del BrawlerEntry
                )
        },
        indices = {@androidx.room.Index(value = "brawlerId"),@androidx.room.Index(value = "tagId")}
)
public class OwnsEntity {
    @PrimaryKey(autoGenerate = true)
    public int ownsId;
    public String tagId;
    public long brawlerId;

    @Ignore
    public OwnsEntity(String tagId, long brawlerId) {
        this.tagId = tagId;
        this.brawlerId = brawlerId;
    }

    // Costruttore vuoto necessario per Room
    public OwnsEntity(){}

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public long getBrawlerId() {
        return brawlerId;
    }

    public void setBrawlerId(long brawlerId) {
        this.brawlerId = brawlerId;
    }
}
