package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
@Dao
public interface StarPowerDAO {
    @Query("SELECT * FROM StarPowerEntry")
    List<StarPowerEntry> getAll();

    @Insert
    void insertAll(StarPowerEntry... starPowerEntries);

    @Delete
    void delete(StarPowerEntry starPowerEntry);

    //TODO: altre queries
}
