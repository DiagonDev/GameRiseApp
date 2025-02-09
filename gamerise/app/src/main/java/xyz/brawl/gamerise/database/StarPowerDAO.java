package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;

@Dao
public interface StarPowerDAO {
    @Query("SELECT * FROM StarPowerEntry WHERE brawlerId = :brawlerId")
    List<StarPowerEntry> getAll(Long brawlerId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<StarPowerEntry> starPowerEntry);
}
