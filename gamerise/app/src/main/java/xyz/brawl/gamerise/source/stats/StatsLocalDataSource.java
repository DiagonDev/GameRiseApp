package xyz.brawl.gamerise.source.stats;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.StatDAO;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.stat.Stat;
import xyz.brawl.gamerise.model.tag.Tag;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class StatsLocalDataSource extends BaseStatsLocalDataSource {
    private final StatDAO statDAO;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public StatsLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.statDAO = gameRiseDatabase.statDao();
        this.tagDao = gameRiseDatabase.tagDAO();
    }

    @Override
    public void getStats(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            statsCallBack.onSuccessFromLocal(statDAO.getStat(tagId));
        });
    }

    @Override
    public void insertStats(Stat stats) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDao.getTag();
            String tagAccount = gameAccountSingleton.getUserTag();
            if (tag.getTag().equals(tagAccount)) {
                Stat allStat = statDAO.getStat(tagAccount);
                if (allStat != null) {
                    String statsList = allStat.trophies + allStat.expLevel + allStat.club.getName() + allStat.soloVictories + allStat.duoVictories + allStat._3vs3Victories;
                    String statsNew = stats.trophies + stats.expLevel + stats.club.getName() + stats.soloVictories + stats.duoVictories + stats._3vs3Victories;
                    if (allStat.getTag().equals(tagAccount) && (!statsList.equals(statsNew))) {
                        statDAO.deleteStat(tagAccount);
                        stats.setTag(tagAccount);
                        statDAO.insertStat(stats);
                    }
                } else {
                    stats.setTag(tagAccount);
                    statDAO.insertStat(stats);
                }
            }
            statsCallBack.onSuccessFromLocal(stats);
        });
    }
}

