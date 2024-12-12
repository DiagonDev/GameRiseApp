package xyz.brawl.gamerise.ui.viewmodels.tag;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.TagRoomDatabase;
import xyz.brawl.gamerise.model.data.tag.Tag;

public class TagViewModel extends ViewModel {
    private final MutableLiveData<List<Tag>> tagsLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Tag>> recentTagsLiveData = new MutableLiveData<>(new ArrayList<>());

    private TagRoomDatabase database;

    public void initDatabase(Context context) {
        database = TagRoomDatabase.getDatabase(context);
    }

    public LiveData<List<Tag>> getTags() {
        return tagsLiveData;
    }

    public LiveData<List<Tag>> getRecentTags() {
        return recentTagsLiveData;
    }

    public void loadRecentTags() {
        if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                List<Tag> recentTags = database.tagDAO().getRecentTags();   // Ottieni i primi 3 tag
                recentTagsLiveData.postValue(recentTags);   // Passa i tag recenti alla UI
            });
        }
    }

    public void addTag(Tag tag) {
        /*List<Tag> currentTags = tagsLiveData.getValue();
        if (currentTags != null) {
            List<Tag> updatedTags = new ArrayList<>(currentTags); //Crea nuova istanza (best practice)
            updatedTags.add(tag);
            tagsLiveData.setValue(updatedTags);
        } else if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                database.tagDAO().insertAll();
                loadRecentTags();
            });
        }*/

        // Verifica se il tag è già presente nella lista dei tag in memoria
        List<Tag> currentTags = tagsLiveData.getValue();
        if (currentTags != null && currentTags.contains(tag)) {
            // Il tag è già presente, quindi non fare nulla
            return;
        } else if (currentTags != null) {       // Aggiungi il tag alla lista in memoria (tagsLiveData)
            List<Tag> updatedTags = new ArrayList<>(currentTags);
            updatedTags.add(tag); // Aggiungi il nuovo tag
            tagsLiveData.setValue(updatedTags);
        }

        if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                // Verifica se il tag esiste già nel database
                List<Tag> existingTags = database.tagDAO().getRecentTags();
                if (existingTags != null && existingTags.contains(tag)) {
                    // Il tag è già presente nel database, quindi non lo aggiungiamo
                    return;
                }

                // Se il tag non è presente, lo aggiungiamo al database
                database.tagDAO().insertAll(tag); // Inserisci il tag nel database
                loadRecentTags(); // Ricarica i tag recenti
            });
        }

    }


    //PER ORA NON LO USIAMO
    public void removeTag(Tag tag) {
        List<Tag> currentTags = tagsLiveData.getValue();
        if (currentTags != null) {
            List<Tag> updatedTags = new ArrayList<>(currentTags);
            updatedTags.remove(tag);
            tagsLiveData.setValue(updatedTags);
        }
    }

    public boolean isTagValid(String input) {
        // Controlla sintassi
        if (input == null || !isTag(input)) {
            return false;
        }

        return true;
    }

    public boolean isTag(String input) {
        if (input != null && !input.startsWith("#") ) {
            input = "#" + input;
        }

        String regex = "#[A-Z0-9]+";

        return input.matches(regex);
    }


}
