package xyz.brawl.gamerise.source.brawler;

import java.util.List;

import xyz.brawl.gamerise.database.BrawlerDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.util.GameAccountSingleton;
import xyz.brawl.gamerise.model.tag.Tag;


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
}
