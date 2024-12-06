package xyz.brawl.gamerise.ui.viewmodels.tag;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;

public class TagViewModel extends ViewModel {
    private final MutableLiveData<List<Tag>> tagsLiveData = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Tag>> getTags() {
        return tagsLiveData;
    }

    public void addTag(Tag tag) {
        List<Tag> currentTags = tagsLiveData.getValue();
        if (currentTags != null) {
            List<Tag> updatedTags = new ArrayList<>(currentTags); //Crea nuova istanza (best practice)
            updatedTags.add(tag);
            tagsLiveData.setValue(updatedTags);
        }
    }

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
