package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

@Dao
public interface OwnsDAO {
    @Query("SELECT * FROM OwnsEntity")
    List<OwnsEntity> getAll();

    @Query("SELECT * FROM OwnsEntity WHERE brawlerId = :brawlerId LIMIT 1")
    OwnsEntity findBrawlerOfPlayerByBrawlerId(String brawlerId);

    @Query("SELECT * FROM OwnsEntity WHERE tagId = :tagId LIMIT 1")
    OwnsEntity findBrawlerOfPlayerByTagId(String tagId);

    @Query("SELECT * FROM OwnsEntity WHERE tagId = :tagId AND brawlerId = :brawlerId LIMIT 1")
    OwnsEntity findBrawlerOfPlayer(String tagId, String brawlerId);

    @Insert
    void insert(OwnsEntity ownsEntity);

    @Insert
    void insertAll(OwnsEntity... ownsEntities);

    @Delete
    void delete(OwnsEntity ownsEntity);

    //TODO: altre queries
}
