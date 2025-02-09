package xyz.brawl.gamerise.model.data.datasource.starPower;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.StarPowerDAO;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;

public class StarPowerLocalDataSource extends  BaseStarPowerLocalDataSource{
    private final StarPowerDAO starPowerDAO;

    public StarPowerLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.starPowerDAO = gameRiseDatabase.starPowerDAO();
    }

    @Override
    public void getStarPower(Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> starPowerCallback.onSuccessFromLocal(starPowerDAO.getAll(brawlerId)));
    }

    @Override
    public void insertStarPowers(List<StarPowerEntry> starPowerEntryList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            starPowerDAO.insertAll(starPowerEntryList);
            starPowerCallback.onSuccessFromLocal(starPowerEntryList);
        });
    }
}
