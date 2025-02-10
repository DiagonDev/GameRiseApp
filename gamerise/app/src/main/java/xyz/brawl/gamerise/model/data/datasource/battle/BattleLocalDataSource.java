package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.BattleDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.tag.Tag;

/**
 * This class represents the local data source for the Battle entity.
 * local because uses the data from the Room database
 */
public class BattleLocalDataSource extends BaseBattleLocalDataSource{
    private final BattleDAO battleDAO;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public BattleLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.battleDAO = gameRiseDatabase.battleDAO();
        this.tagDao = gameRiseDatabase.tagDAO();
    }

    @Override
    public void getBattles(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> battleLogCallback.onSuccessFromLocal(battleDAO.getAll(tagId)));
    }

    @Override
    public void insertBattles(List<Battle> battleList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            Tag tag = tagDao.getTag();
            String tagAccount = gameAccountSingleton.getUserTag();
            if(tag.getTag().equals(tagAccount)) {
                for(Battle battle : battleList) {
                    battle.setTagId(tag.getTag());
                }
                battleDAO.insertAll(battleList);
            }
            GameAccountSingleton.getInstance().setLastUpdate(System.currentTimeMillis());
            battleLogCallback.onSuccessFromLocal(battleList);
        });
    }
}
