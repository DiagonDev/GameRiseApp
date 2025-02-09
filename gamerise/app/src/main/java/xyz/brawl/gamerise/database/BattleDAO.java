package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

@Dao
public interface BattleDAO {
    @Query("SELECT * FROM Battle WHERE tagId = :tagId ORDER BY battleId DESC")
    List<Battle> getAll(String tagId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Battle> battles);
}