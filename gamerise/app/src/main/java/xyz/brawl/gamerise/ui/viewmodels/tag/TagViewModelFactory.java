package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.tag.TagRepository;

public class TagViewModelFactory implements ViewModelProvider.Factory {
    private final TagRepository tagRepository;

    public TagViewModelFactory(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TagViewModel(tagRepository);
    }
}
