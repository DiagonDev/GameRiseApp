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
    @Query("SELECT * FROM Stat")
    List<Stat> getAll();

    @Query("SELECT * FROM Stat WHERE tag = :tagName LIMIT 1")
    Stat findStatByName(String tagName); // Cerca una Stat per tag

    @Insert
    void insert(Stat stat);

    @Insert
    void insertAll(Stat... stats);

    @Delete
    void delete(Stat stat);

    //TODO: altre queries

}
