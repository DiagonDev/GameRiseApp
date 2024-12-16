package xyz.brawl.gamerise.database;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;

public interface GadgetDAO {
    @Query("SELECT * FROM GadgetEntry")
    List<GadgetEntry> getAll();

    @Insert
    void insertAll(GadgetEntry... gadgetEntries);

    @Delete
    void delete(GadgetEntry gadgetEntry);

    //TODO: altre queries
}
