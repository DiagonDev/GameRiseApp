package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.stat.Stat;
@Dao
public interface StatDAO {
    @Query("SELECT * FROM Stat")
    List<Stat> getAll();

    @Insert
    void insertAll(Stat... stats);

    @Delete
    void delete(Stat stat);

    //TODO: altre queries

}
