package xyz.brawl.gamerise.model.repository.tag;

import xyz.brawl.gamerise.model.data.tag.Tag;


public interface TagCallback {
    //TODO: leo deve modificare qui
    void onSuccessFromRemote(Tag tag, long lastUpdate);
    void onFailureFromRemote(String errorMessage);
    //qui è giusto
    void onSuccessFromLocal(Tag tag);
    void onFailureFromLocal(Exception exception);
}