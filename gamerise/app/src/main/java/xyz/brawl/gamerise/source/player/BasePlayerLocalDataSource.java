package xyz.brawl.gamerise.source.player;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.repository.player.PlayerCallBack;

public abstract class BasePlayerLocalDataSource {
    public abstract void insertPlayerData(List<BrawlerEntry> brawlers, List<StarPowerEntry> starPowers, List<GadgetEntry> gadgets);
}
