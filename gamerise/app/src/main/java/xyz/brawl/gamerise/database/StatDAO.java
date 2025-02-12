package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import xyz.brawl.gamerise.model.stat.Stat;

@Dao
public interface StatDAO {
    @Query("SELECT * FROM Stat WHERE tag = :tagName")
    Stat getStat(String tagName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStat(Stat stat);

   @Query("DELETE FROM Stat WHERE tag = :tagName")
    void deleteStat(String tagName);
}
