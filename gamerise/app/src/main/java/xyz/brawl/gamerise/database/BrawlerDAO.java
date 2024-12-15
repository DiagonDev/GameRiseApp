package xyz.brawl.gamerise.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.stat.Stat;

public interface BrawlerDAO {
    @Query("SELECT * FROM BrawlerEntry")
    List<Stat> getAll();

    @Insert
    void insertAll(BrawlerEntry... brawlerEntries);

    @Delete
    void delete(BrawlerEntry brawlerEntry);

    //TODO: altre queries
}
