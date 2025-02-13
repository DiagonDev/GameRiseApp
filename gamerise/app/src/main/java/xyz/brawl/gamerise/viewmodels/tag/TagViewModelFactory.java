package xyz.brawl.gamerise.viewmodels.tag;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.repository.player.PlayerRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;
import xyz.brawl.gamerise.repository.user.UserRepository;

public class TagViewModelFactory implements ViewModelProvider.Factory {
    private final TagRepository tagRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    public TagViewModelFactory(TagRepository tagRepository, UserRepository userRepository, PlayerRepository playerRepository) {
        this.tagRepository = tagRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TagViewModel(tagRepository, userRepository, playerRepository);
    }
}
