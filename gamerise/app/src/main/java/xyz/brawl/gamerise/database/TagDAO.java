package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM Player")
    Tag getTag();

    @Insert
    void insert(Tag tag); // Inserisci nuovi tag

    @Delete
    void delete(Tag tag); // Elimina un tag
}