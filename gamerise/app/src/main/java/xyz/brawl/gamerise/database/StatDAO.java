package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import xyz.brawl.gamerise.model.data.stat.Stat;

@Dao
public interface StatDAO {
    @Query("SELECT * FROM Stat WHERE tag = :tagName")
    Stat getStat(String tagName);

    @Insert
    void insertStat(Stat stat);

    @Query("DELETE FROM Stat WHERE tag = :tagName")
    Stat deleteStat(String tagName);
}
