package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
@Dao
public interface BrawlerDAO {
    @Query("SELECT * FROM BrawlerEntry")
    List<BrawlerEntry> getAll();

    @Insert
    void insertAll(BrawlerEntry... brawlerEntries);

    @Delete
    void delete(BrawlerEntry brawlerEntry);

    //TODO: altre queries
}
