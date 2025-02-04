package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;

@Dao
public interface GadgetDAO {
    @Query("SELECT * FROM GadgetEntry WHERE brawlerId = :brawlerId")
    List<GadgetEntry> getAll(Long brawlerId);

    @Insert
    void insertAll(List<GadgetEntry> gadgetEntries);
}
