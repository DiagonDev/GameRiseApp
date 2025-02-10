package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.List;

import xyz.brawl.gamerise.database.BrawlerDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;


public class BrawlersLocalDataSource extends BaseBrawlersLocalDataSource {
    private final BrawlerDAO brawlerDAO;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public BrawlersLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.brawlerDAO = gameRiseDatabase.brawlerDAO();
        this.tagDao = gameRiseDatabase.tagDAO();
    }
    @Override
    public void getBrawlers(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> brawlersCallBack.onSuccessFromLocal(brawlerDAO.getAll(tagId)));
    }

    @Override
    public void insertBrawlers(List<BrawlerEntry> brawlerList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDao.getTag();
            String tagAccount = gameAccountSingleton.getUserTag();
            if(tag.getTag().equals(tagAccount)) {
                for(BrawlerEntry brawler : brawlerList) {
                    brawler.setTagId(tag.getTag());
                }
                brawlerDAO.insertAll(brawlerList);
            }
            brawlersCallBack.onSuccessFromLocal(brawlerList);
        });
    }
}
