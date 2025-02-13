package xyz.brawl.gamerise.source.brawler;

import xyz.brawl.gamerise.database.BrawlerDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.util.GameAccountSingleton;


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
