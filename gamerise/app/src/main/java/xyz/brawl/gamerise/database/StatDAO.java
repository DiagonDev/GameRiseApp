package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.model.data.tag.Tag;

@Dao
public interface StatDAO {
    @Query("SELECT * FROM Stat WHERE tag = :tagName")
    List<Stat> getAll(String tagName);

    @Insert
    void insertAll(List<Stat> stats);
}
