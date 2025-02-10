package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.model.repository.user.UserRepository;

public class TagViewModelFactory implements ViewModelProvider.Factory {
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public TagViewModelFactory(TagRepository tagRepository, UserRepository userRepository) {
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TagViewModel(tagRepository, userRepository);
    }
}
