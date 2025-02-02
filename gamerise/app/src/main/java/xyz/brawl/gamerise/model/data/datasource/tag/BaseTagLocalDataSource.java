package xyz.brawl.gamerise.model.data.datasource.tag;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

import xyz.brawl.gamerise.model.repository.tag.TagCallback;

public abstract class BaseTagLocalDataSource {
    protected TagCallback tagCallback;

    public void setBattleLogCallback(TagCallback tagCallback) {
        this.tagCallback = tagCallback;
    }

    public abstract void getRecentTags();

    public abstract void insertTags(String tagId);
    public abstract void findTagByName(String tagName);
}
