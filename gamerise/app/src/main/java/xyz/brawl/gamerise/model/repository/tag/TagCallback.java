package xyz.brawl.gamerise.model.repository.tag;

import java.util.List;

import xyz.brawl.gamerise.model.data.tag.Tag;


public interface TagCallback {
    //TODO: leo deve modificare qui
    void onSuccessFromRemote(List<Tag> tags, long lastUpdate);
    void onFailureFromRemote(String errorMessage);
    //qui è giusto
    void onSuccessFromLocal(List<Tag> tags);
    void onFailureFromLocal(Exception exception);
}