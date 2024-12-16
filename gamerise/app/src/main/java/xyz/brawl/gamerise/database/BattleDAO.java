package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;
@Dao
public interface BattleDAO {
    @Query("SELECT * FROM Battle")
    List<Battle> getAll();

    @Insert
    void insertAll(Battle... battles);

    @Delete
    void delete(Battle battle);

    //TODO: altre queries

}
