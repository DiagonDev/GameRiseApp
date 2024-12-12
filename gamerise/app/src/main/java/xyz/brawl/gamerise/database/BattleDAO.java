/*package xyz.brawl.gamerise.database;

import xyz.brawl.gamerise.model.data.battle.api.BattleEntry;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BattleDAO {
    @Query("SELECT * FROM BattleEntry")
    List<BattleEntry> getAll();

    @Insert
    void insertAll(BattleEntry... users);

    @Delete
    void delete(BattleEntry user);
}*/