package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.List;

import xyz.brawl.gamerise.database.BattleDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogCallback;

/**
 * This class represents the local data source for the Battle entity.
 * local because uses the data from the Room database
 */
public class BattleLocalDataSource extends BaseBattleLocalDataSource{
    private final BattleDAO battleDAO;

    public BattleLocalDataSource(BattleDAO battleDAO) {
        this.battleDAO = battleDAO;
    }

    @Override
    public void getBattles() {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            battleLogCallback.onSuccessFromLocal(battleDAO.getAll());
        });

    }

    @Override
    public void deleteBattles(Battle battle) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            List<Battle> allBattles = battleDAO.getAll();
            if(battles != null) {
                for (Battle battle : battles) {
                    if (battleList.contains(battle)) {
                        battleDAO.delete(battle);
                        battleLogCallback.onSuccessFromLocal(battleDAO.getAll());
                    } else battleLogCallback.onFailureFromLocal(null);
                }
            } else battleLogCallback.onFailureFromLocal(null);
        });
    }

    @Override
    public void insertBattles(List<Battle> battleList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            List<Battle> allBattles = battleDAO.getAll();
            if(battles != null){
                for(Battle battle : battles){
                    if(battleList.contains(battle))
                        battleList.set(battleList.indexOf(battle), battle);
                }
            }
            battleDAO.insertAll(battleList);
            battleLogCallback.onSuccessFromLocal(battleDAO.getAll());
        });
    }
}
