package xyz.brawl.gamerise.source.player;

import java.util.List;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.PlayerTransactionDao;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class PlayerLocalDataSource extends BasePlayerLocalDataSource {
    private final PlayerTransactionDao playerTransactionDao;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();


    public PlayerLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.playerTransactionDao = gameRiseDatabase.playerTransactionDao();
        this.tagDao = gameRiseDatabase.tagDAO();
    }

    public void insertPlayerData(List<BrawlerEntry> brawlers, List<StarPowerEntry> starPowers, List<GadgetEntry> gadgets) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDao.getTag();
            String tagAccount = gameAccountSingleton.getUserTag();
            if(tag.getTag().equals(tagAccount)) {
                for(BrawlerEntry brawler : brawlers) {
                    brawler.setTagId(tag.getTag());
                }
                playerTransactionDao.insertPlayerData(brawlers, starPowers, gadgets);
            }
            //playerCallBack.onSuccessFromLocal(brawlers, starPowers, gadgets);
        });

    }
}
