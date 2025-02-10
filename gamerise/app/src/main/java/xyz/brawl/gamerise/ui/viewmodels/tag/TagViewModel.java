package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.model.repository.user.UserRepository;

public class TagViewModel extends ViewModel {
    public static final String TAG = TagViewModel.class.getSimpleName();
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final PlayerRepository playerRepository;
    private MutableLiveData<Result> userMutableLiveData;
    private MutableLiveData<Result> userTagMutableLiveData;
    private MutableLiveData<Result> tagLiveData;

    public TagViewModel(TagRepository tagRepository, UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.playerRepository = playerRepository;
    }

    private void getUserData(String token) {
        userMutableLiveData = userRepository.getGoogleUser(token);
    }

    public void insertTag(Tag tag) {
        tagRepository.insertTag(tag);
        playerRepository.fetchPlayer(tag.getTag());
    }

    public MutableLiveData<Result> getSavedTag() {
        tagLiveData = tagRepository.fetchTag();
        return tagLiveData;
    }

    public MutableLiveData<Result> getGoogleUserMutableLiveData(String token) {
        if (userMutableLiveData == null) {
            getUserData(token);
        }
        return userMutableLiveData;
    }
    public MutableLiveData<Result> getUserTag(String sessionId) {
        if (sessionId != null && !sessionId.isEmpty()) {
            userTagMutableLiveData = userRepository.getUserTag(sessionId);
        }
        return userTagMutableLiveData;
    }
    public boolean isTagValid(String input) {
        return input != null && isTag(input);
    }

    public boolean isTag(String input) {
        // regex per la tag
        if (input != null && !input.startsWith("#") ) {
            input = "#" + input;
        }

        String regex = "#[A-Z0-9]+";

        return input.matches(regex);
    }
}
