package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface OwnsDAO {
    @Query("SELECT * FROM OwnsEntity")
    List<OwnsEntity> getAll();

    @Insert
    void insertAll(OwnsEntity... ownsEntities);

    @Delete
    void delete(OwnsEntity ownsEntity);

    //TODO: altre queries
}
