package xyz.brawl.gamerise.model.data.datasource.tag;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.tag.TagCallback;

public abstract class BaseTagLocalDataSource {
    protected TagCallback tagCallback;

    public void setBattleLogCallback(TagCallback tagCallback) {
        this.tagCallback = tagCallback;
    }

    public abstract void getTag();

    public abstract void insertTag(Tag tag);

    public abstract void deleteTag(Tag tag);
}
