package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import xyz.brawl.gamerise.model.tag.Tag;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM Player")
    Tag getTag();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tag tag); // Inserisci nuovi tag

    @Delete
    void delete(Tag tag); // Elimina un tag
}