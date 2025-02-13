package xyz.brawl.gamerise.repository.tag;

import xyz.brawl.gamerise.model.tag.Tag;

public interface TagCallback {
    void onSuccessFromRemote(Tag tag, long lastUpdate);
    void onFailureFromRemote(String errorMessage);
    void onSuccessFromLocal(Tag tag);
    void onFailureFromLocal(Exception exception);
}