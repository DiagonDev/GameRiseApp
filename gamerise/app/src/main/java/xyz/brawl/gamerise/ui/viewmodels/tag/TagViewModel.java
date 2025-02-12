package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.model.GoogleUser;
import xyz.brawl.gamerise.repository.player.PlayerRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;
import xyz.brawl.gamerise.repository.user.UserRepository;

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

    public void insertTag(Tag tag) {
        tagRepository.insertTag(tag);
        playerRepository.fetchPlayer(tag.getTag());
    }

    public MutableLiveData<Result> fetchTag() {
        tagLiveData = tagRepository.fetchTag();
        return tagLiveData;
    }

    public GoogleUser getLoggedGoogleUser() {
        return userRepository.getLoggedUser();
    }

    public void logoutGoogleUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = userRepository.logout();
        } else {
            userRepository.logout();
        }
    }

    public MutableLiveData<Result> getGoogleUserMutableLiveData(String token) {
        if (userMutableLiveData == null) {
            userMutableLiveData = userRepository.getGoogleUser(token);
        }
        return userMutableLiveData;
    }

    public void saveTagOnFirebase(String tag, String sessionId) {
        userRepository.saveUserTag(tag, sessionId);
    }

    public MutableLiveData<Result> getFirebaseTag_TagVM(String sessionId) {
        if (sessionId != null && !sessionId.isEmpty())
            userTagMutableLiveData = userRepository.getUserTag$UserRepository(sessionId);
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
