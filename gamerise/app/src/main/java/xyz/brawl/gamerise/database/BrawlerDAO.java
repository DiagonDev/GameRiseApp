package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;

@Dao
public interface BrawlerDAO {
    @Query("SELECT * FROM BrawlerEntry b WHERE b.tagId = :tagId ORDER BY b.id ASC")
    List<BrawlerEntry> getAll(String tagId);
}