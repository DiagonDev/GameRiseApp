package xyz.brawl.gamerise.databaseDeprecated;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Database(entities = {Tag.class}, version = 2, exportSchema = false)
public abstract class TagRoomDatabase extends RoomDatabase {
    public abstract TagDAO tagDAO();

    private static volatile TagRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TagRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TagRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TagRoomDatabase.class, "TagDatabase")
                            .fallbackToDestructiveMigrationFrom(1, 2) // Specifica le versioni per il fallback
                            .allowMainThreadQueries() // Questo è utile solo per test, meglio evitare in produzione
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}