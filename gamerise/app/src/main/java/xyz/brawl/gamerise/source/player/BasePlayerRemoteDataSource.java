package xyz.brawl.gamerise.source.player;

import xyz.brawl.gamerise.repository.player.PlayerCallBack;

public abstract class BasePlayerRemoteDataSource {
    protected PlayerCallBack playerCallBack;
    public void setPlayerCallBack(PlayerCallBack playerCallBack) {
        this.playerCallBack = playerCallBack;
    }

    public abstract void getPlayer(String tagId);
}
