package xyz.brawl.gamerise.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Database(entities = {Tag.class}, version = 1)
public abstract class TagRoomDatabase extends RoomDatabase {
    public abstract TagDAO tagDAO();

    private static volatile TagRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TagRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (BattleRoomDatabase.class){
                if(INSTANCE == null){
                    //da cambiare da fare attraverso la view
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TagRoomDatabase.class,  "TagDatabse").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}