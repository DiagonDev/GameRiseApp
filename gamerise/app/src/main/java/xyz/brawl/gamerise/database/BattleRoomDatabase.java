package xyz.brawl.gamerise.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xyz.brawl.gamerise.model.data.battle.api.BattleEntry;

@Database(entities = {BattleEntry.class}, version = 1)
public abstract class BattleRoomDatabase extends RoomDatabase {
    public abstract BattleDAO battleDAO();

    private static volatile BattleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BattleRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (BattleRoomDatabase.class){
                if(INSTANCE == null){
                    //da cambiare da fare attraverso la view
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BattleRoomDatabase.class,  "BattleDatabse").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}