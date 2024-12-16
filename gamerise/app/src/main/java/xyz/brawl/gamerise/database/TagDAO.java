package xyz.brawl.gamerise.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Dao
public interface TagDAO {
    @Query("SELECT * FROM Player ORDER BY rowid DESC")
    List<Tag> getRecentTags(); // Seleziona i 3 tag più recenti

    @Query("SELECT * FROM Player WHERE tag = :tagName LIMIT 1")
    Tag findTagByName(String tagName); // Cerca un tag per nome

    @Insert
    void insertAll(Tag... tags); // Inserisci nuovi tag

    @Delete
    void delete(Tag tag); // Elimina un tag
}