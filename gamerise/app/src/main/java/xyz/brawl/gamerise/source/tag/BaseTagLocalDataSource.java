package xyz.brawl.gamerise.source.tag;

import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.repository.tag.TagCallback;

public abstract class BaseTagLocalDataSource {
    protected TagCallback tagCallback;

    public void setBattleLogCallback(TagCallback tagCallback) {
        this.tagCallback = tagCallback;
    }

    public abstract void getTag();

    public abstract void insertTag(Tag tag);

    public abstract void deleteTag(Tag tag);
}
