package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.stat.Stat;

@Dao
public interface BrawlerDAO {
    @Query("SELECT * FROM BrawlerEntry")
    List<BrawlerEntry> getAll();

    @Query("SELECT * FROM BrawlerEntry WHERE id = :id LIMIT 1")
    BrawlerEntry findBrawlerById(long id); // Cerca un brawler per l'id

    @Insert
    void insert(BrawlerEntry brawlerEntry);

    @Insert
    void insertAll(BrawlerEntry... brawlerEntries);

    @Delete
    void delete(BrawlerEntry brawlerEntry);

    //TODO: altre queries
}
