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
    @Query("SELECT * FROM BrawlerEntry b WHERE b.tagId = :tagId ORDER BY b.id DESC")
    List<BrawlerEntry> getAll(String tagId);

    @Insert
    void insertAll(List<BrawlerEntry> brawlerEntries);
}