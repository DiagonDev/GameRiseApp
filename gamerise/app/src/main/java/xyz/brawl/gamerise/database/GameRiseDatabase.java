package xyz.brawl.gamerise.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.stat.Stat;
import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.util.Constants;

@Database(entities = {Stat.class, Tag.class, Battle.class, BrawlerEntry.class, StarPowerEntry.class, GadgetEntry.class}
        ,version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class GameRiseDatabase extends RoomDatabase {

    public abstract StatDAO statDao();
    public abstract BattleDAO battleDAO();
    public abstract BrawlerDAO brawlerDAO();
    public abstract TagDAO tagDAO();
    public abstract StarPowerDAO starPowerDAO();
    public abstract GadgetDAO gadgetDAO();

    private static volatile GameRiseDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static GameRiseDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GameRiseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GameRiseDatabase.class, "GameRiseDatabase")
                            .fallbackToDestructiveMigration() // Specifica le versioni per il fallback
                            .allowMainThreadQueries() // Questo è utile solo per test, meglio evitare in produzione
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
