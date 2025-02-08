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
    public void insertStarPowers(List<StarPowerEntry> starPowerEntryList, Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            List<StarPowerEntry> allStarPower = starPowerDAO.getAll(brawlerId);
            List<StarPowerEntry> toInsertOrUpdate = new ArrayList<>();
            if(allStarPower != null){
                for(StarPowerEntry newStarPower : starPowerEntryList){
                    if(!allStarPower.contains(newStarPower))
                        toInsertOrUpdate.add(newStarPower);
                }
                if (!toInsertOrUpdate.isEmpty()) {
                    starPowerDAO.insertAll(toInsertOrUpdate);
                }
                starPowerCallback.onSuccessFromLocal(starPowerDAO.getAll(brawlerId));
            } else starPowerDAO.insertAll(starPowerEntryList);
        });
    }
}
