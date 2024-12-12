package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM Tag ORDER BY rowid DESC LIMIT 3")
    List<Tag> getRecentTags(); // Seleziona i 3 tag più recenti

    @Insert
    void insertAll(Tag... tags); // Inserisci nuovi tag
//insert or ignore?
    @Delete
    void delete(Tag tag); // Elimina un tag
}