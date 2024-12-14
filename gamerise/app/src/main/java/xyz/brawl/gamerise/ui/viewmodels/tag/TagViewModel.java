package xyz.brawl.gamerise.ui.viewmodels.tag;

import android.content.Context;

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
        // Aggiungi il tag alla lista in memoria (tagsLiveData)
        List<Tag> currentTags = tagsLiveData.getValue();
        if (currentTags != null && currentTags.contains(tag)) {
            // Il tag è già presente, quindi non fare nulla
            return;
        } else if (currentTags != null) {
            // Aggiungi il tag alla lista in memoria (tagsLiveData)
            List<Tag> updatedTags = new ArrayList<>(currentTags);
            updatedTags.add(tag); // Aggiungi il nuovo tag
            tagsLiveData.setValue(updatedTags);
        }

        // Salva il tag nel database ed elimina i meno recenti se necessario
        if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                database.tagDAO().insertAll(tag); // Inserisci il nuovo tag nel database

                // Controlla se ci sono più di 3 tag nel database
                List<Tag> allTags = database.tagDAO().getRecentTags();
                if (allTags.size() > 3) {
                    // Rimuovi il tag più vecchio
                    Tag oldestTag = allTags.get(allTags.size() - 1); // Ultimo nella lista
                    database.tagDAO().delete(oldestTag);
                }

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
        return input != null && isTag(input);
    }

    public boolean isTag(String input) {
        if (input != null && !input.startsWith("#") ) {
            input = "#" + input;
        }

        String regex = "#[A-Z0-9]+";

        return input.matches(regex);
    }

    public void saveRecentTagsToDatabase() {
        if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                List<Tag> tagsToSave = tagsLiveData.getValue();
                if (tagsToSave != null) {
                    // Elimina tutti i tag esistenti
                    for (Tag tag : database.tagDAO().getRecentTags()) {
                        database.tagDAO().delete(tag);
                    }
                    // Salva solo i primi 3 tag
                    for (int i = 0; i < Math.min(tagsToSave.size(), 3); i++) {
                        database.tagDAO().insertAll(tagsToSave.get(i));
                    }
                }
            });
        }
    }

}
