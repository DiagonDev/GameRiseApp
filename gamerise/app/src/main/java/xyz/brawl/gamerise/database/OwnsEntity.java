package xyz.brawl.gamerise.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.tag.Tag;

@Entity
public class OwnsEntity {
    @PrimaryKey(autoGenerate = true)
    public int ownsId;

    @Embedded(prefix = "tag_")
    public Tag tag;

    @Embedded(prefix = "brawler_")
    public BrawlerEntry brawlerEntry;


    public BrawlerEntry getBrawlerEntry() {
        return brawlerEntry;
    }

    public void setBrawlerEntry(BrawlerEntry brawlerEntry) {
        this.brawlerEntry = brawlerEntry;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
