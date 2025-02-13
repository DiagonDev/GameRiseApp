package xyz.brawl.gamerise.source.starPower;

import java.util.List;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.StarPowerDAO;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.util.GameAccountSingleton;
import xyz.brawl.gamerise.model.tag.Tag;

public class StarPowerLocalDataSource extends  BaseStarPowerLocalDataSource{
    private final StarPowerDAO starPowerDAO;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public StarPowerLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.starPowerDAO = gameRiseDatabase.starPowerDAO();
        this.tagDao = gameRiseDatabase.tagDAO();
    }

    @Override
    public void getStarPower(Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> starPowerCallback.onSuccessFromLocal(starPowerDAO.getAll(brawlerId)));
    }
}
