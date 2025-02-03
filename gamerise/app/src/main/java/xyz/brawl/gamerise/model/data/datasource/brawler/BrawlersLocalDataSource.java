package xyz.brawl.gamerise.model.data.datasource.brawler;

import java.util.List;

import xyz.brawl.gamerise.database.BrawlerDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;


public class BrawlersLocalDataSource extends BaseBrawlersLocalDataSource {
    private final BrawlerDAO brawlerDAO;
    public BrawlersLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.brawlerDAO = gameRiseDatabase.brawlerDAO();
    }
    @Override
    public void getBrawlers(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            brawlersCallBack.onSuccessFromLocal(brawlerDAO.getAll(tagId));
        });
    }

    @Override
    public void insertBrawlers(List<BrawlerEntry> brawlerList, String tagId) {

    }
}
