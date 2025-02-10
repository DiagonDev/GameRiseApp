package xyz.brawl.gamerise.ui.viewmodels.tag;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.tag.TagRepository;
import xyz.brawl.gamerise.model.repository.user.UserRepository;

public class TagViewModel extends ViewModel {
    public static final String TAG = TagViewModel.class.getSimpleName();
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private MutableLiveData<Result> userMutableLiveData;
    private MutableLiveData<Result> userPreferencesMutableLiveData;
    private boolean authenticationError;

    public TagViewModel(TagRepository tagRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        authenticationError = false;
    }


    public MutableLiveData<Result> getGoogleUserMutableLiveData(String token) {
        if (userMutableLiveData == null) {
            getUserData(token);
        }
        return userMutableLiveData;
    }

    private void getUserData(String token) {
        userMutableLiveData = userRepository.getGoogleUser(token);
    }

    public void insertTag(Tag tag) {
        tagRepository.insertTag(tag);
    }

    public void setAuthenticationError(boolean authenticationError) {
        this.authenticationError = authenticationError;
    }

    public MutableLiveData<Result> getUserPreferences(String idToken) {
        if (idToken != null) {
            userPreferencesMutableLiveData = userRepository.getUserPreferences(idToken);
        }
        return userPreferencesMutableLiveData;
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
