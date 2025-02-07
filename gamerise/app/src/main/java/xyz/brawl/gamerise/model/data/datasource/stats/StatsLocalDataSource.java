package xyz.brawl.gamerise.model.data.datasource.stats;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.StatDAO;
import xyz.brawl.gamerise.model.data.stat.Stat;

public class StatsLocalDataSource extends BaseStatsLocalDataSource {
    private final StatDAO statDAO;

    public StatsLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.statDAO = gameRiseDatabase.statDao();
    }

    @Override
    public void getStats(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            statsCallBack.onSuccessFromLocal(statDAO.getStat(tagId));
        });
    }


    @Override
    public void insertStats(Stat stats, String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Stat allStat = statDAO.getStat(tagId);

            if(allStat != null){
                if(allStat.getTag().equals(tagId)){
                    //statDAO.deleteStat(tagId);
                    statDAO.insertStat(stats);
                }
            } else statDAO.insertStat(stats);
        });

    }
}

