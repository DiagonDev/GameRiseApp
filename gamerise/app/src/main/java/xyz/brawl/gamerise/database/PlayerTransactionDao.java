package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;

@Dao
public interface PlayerTransactionDao {
    @Transaction
    default void insertPlayerData(List<BrawlerEntry> brawlers, List<StarPowerEntry> starPowers, List<GadgetEntry> gadgets) {
        insertBrawlers(brawlers);
        insertStarPowers(starPowers);
        insertGadgets(gadgets);
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertBrawlers(List<BrawlerEntry> brawlers);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStarPowers(List<StarPowerEntry> starPowers);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGadgets(List<GadgetEntry> gadgets);
}

