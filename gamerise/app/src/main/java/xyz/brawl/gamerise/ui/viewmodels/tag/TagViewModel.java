package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;

public class TagViewModel extends ViewModel{
    private MutableLiveData<Result> tagLiveData;
    private final TagRepository tagRepository;
    private final PlayerRepository playerRepository;

    public TagViewModel(TagRepository tagRepository, PlayerRepository playerRepository) {
        this.tagRepository = tagRepository;
        this.playerRepository = playerRepository;
    }


    public void addTag(Tag tag) {
        tagRepository.insertTag(tag);
        playerRepository.fetchPlayer(tag.getTag());
        //
    }

    public MutableLiveData<Result> getSavedTag() {
        tagLiveData = tagRepository.fetchTag();
        return tagLiveData;
    }


    public boolean isTagValid(String input) {
        // Controlla sintassi
        return input != null && isTag(input);
    }

    public boolean isTag(String input) {
        if (input != null && !input.startsWith("#")) {
            input = "#" + input;
        }

        String regex = "#[A-Z0-9]+";

        return input.matches(regex);
    }
}
