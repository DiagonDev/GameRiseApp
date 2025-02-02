package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

@Dao
public interface BattleDAO {
    @Query("SELECT * FROM Battle WHERE tagId = :tagId ORDER BY battleId DESC")
    List<Battle> getAll(String tagId);

    @Query("SELECT * FROM Battle WHERE battleId = :battleId LIMIT 1")
    Battle findBattleByBattleId(String battleId); // Cerca un battle per battleId

    @Query("SELECT * FROM Battle WHERE tagId = :tagId LIMIT 1")
    Battle findBattleByTagId(String tagId); // Cerca un battle per tagId

    @Query("SELECT * FROM Battle WHERE tagId = :tagId AND battleId = :battleId LIMIT 1")
    Battle findBattleOfPlayer(String tagId, String battleId); // Cerca un battle per tagId && battleId

    @Insert
    void insertAll(List<Battle> battles);

    @Delete
    void delete(Battle battle);

    //TODO: altre queries

}
