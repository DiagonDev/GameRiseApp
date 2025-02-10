package xyz.brawl.gamerise.model.data.datasource.player;

import xyz.brawl.gamerise.model.repository.player.PlayerCallBack;

public abstract class BasePlayerRemoteDataSource {
    protected PlayerCallBack playerCallBack;
    public void setPlayerCallBack(PlayerCallBack playerCallBack) {
        this.playerCallBack = playerCallBack;
    }

    public abstract void getPlayer(String tagId);
}
