package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;

public class TagViewModelFactory implements ViewModelProvider.Factory {
    private final TagRepository tagRepository;
    private final PlayerRepository playerRepository;

    public TagViewModelFactory(TagRepository tagRepository, PlayerRepository playerRepository) {
        this.tagRepository = tagRepository;
        this.playerRepository = playerRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TagViewModel(tagRepository, playerRepository);
    }
}
