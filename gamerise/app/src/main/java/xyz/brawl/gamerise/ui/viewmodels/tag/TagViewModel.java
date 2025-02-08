package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;

public class TagViewModel extends ViewModel{
    //private MutableLiveData<Result> tagLiveData;
    private final TagRepository tagRepository;

    public TagViewModel(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public void addTag(Tag tag) {
        //tagLiveData = tagRepository.insertTag(tag);
        tagRepository.insertTag(tag);
        //DownloadDataFacade facade = new DownloadDataFacade(getApplication());
        // Salva il tag nel database ed elimina i meno recenti se necessario
        /* ridondate se uso facade
        if (database != null) {
            GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                // Controlla se il tag esiste già nel database
                Tag existingTag = database.tagDao().findTagByName(tag.getTag());
                if (existingTag != null) {
                    // Rimuovi il tag esistente dalla vecchia posizione
                    database.tagDao().delete(existingTag);
                }

                database.tagDao().insertAll(tag); // Inserisci il nuovo tag nel database

                // Controlla se ci sono più di 3 tag nel database
                List<Tag> allTags = database.tagDao().getRecentTags();
                if (allTags.size() > 3) {
                    // Rimuovi il tag più vecchio
                    Tag oldestTag = allTags.get(allTags.size() - 1); // Ultimo nella lista
                    database.tagDao().delete(oldestTag);
                    allTags.remove(oldestTag);
                }
            });
        }*/


        /*// Aggiorna la lista runtime (tagsLiveData)
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
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
        });*/

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
