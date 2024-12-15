package xyz.brawl.gamerise.ui.viewmodels.tag;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import xyz.brawl.gamerise.databaseDeprecated.TagRoomDatabase;
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
                unifyTags(recentTags);  // Unifica i tag del database con quelli runtime
            });
        }
    }

    public void addTag(Tag tag) {

        // Salva il tag nel database ed elimina i meno recenti se necessario
        if (database != null) {
            TagRoomDatabase.databaseWriteExecutor.execute(() -> {
                // Controlla se il tag esiste già nel database
                Tag existingTag = database.tagDAO().findTagByName(tag.getTag());
                if (existingTag != null) {
                    // Rimuovi il tag esistente dalla vecchia posizione
                    database.tagDAO().delete(existingTag);
                }


                database.tagDAO().insertAll(tag); // Inserisci il nuovo tag nel database

                // Controlla se ci sono più di 3 tag nel database
                List<Tag> allTags = database.tagDAO().getRecentTags();
                if (allTags.size() > 3) {
                    // Rimuovi il tag più vecchio
                    Tag oldestTag = allTags.get(allTags.size() - 1); // Ultimo nella lista
                    database.tagDAO().delete(oldestTag);
                    allTags.remove(oldestTag);
                }

                loadRecentTags(); // Ricarica i tag recenti
            });
        }
        // Aggiorna la lista runtime (tagsLiveData)
        TagRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Tag> currentTags = tagsLiveData.getValue();
            if (currentTags == null) {
                currentTags = new ArrayList<>();
            }

            // Se il tag esiste già nella lista runtime, lo spostiamo in cima
            if (currentTags.contains(tag)) {
                currentTags.remove(tag); // Rimuovi la vecchia occorrenza
            }
            currentTags.add(0, tag); // Aggiungi il tag più recente in cima
            tagsLiveData.postValue(currentTags);
        });

    }

    private void unifyTags(List<Tag> recentTags) {
        // Usa un Set per evitare duplicati
        Set<String> seenTags = new HashSet<>();
        List<Tag> unifiedTags = new ArrayList<>();

        // Aggiungi i tag dal database (recenti)
        if (recentTags != null) {
            for (Tag tag : recentTags) {
                if (seenTags.add(tag.getTag())) {
                    unifiedTags.add(tag);
                }
            }
        }

        // Aggiungi i tag runtime (da tagsLiveData)
        List<Tag> runtimeTags = tagsLiveData.getValue();
        if (runtimeTags != null) {
            for (Tag tag : runtimeTags) {
                if (seenTags.add(tag.getTag())) {
                    unifiedTags.add(tag);
                }
            }
        }

        // Ordina i tag unificati per timestamp in ordine decrescente
        unifiedTags.sort((tag1, tag2) -> Long.compare(tag2.getTimestamp(), tag1.getTimestamp()));

        // Aggiorna i tag unificati
        tagsLiveData.postValue(unifiedTags);
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
}
