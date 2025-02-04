package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.BattleDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.battle.Battle;

/**
 * This class represents the local data source for the Battle entity.
 * local because uses the data from the Room database
 */
public class BattleLocalDataSource extends BaseBattleLocalDataSource{
    private final BattleDAO battleDAO;

    public BattleLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.battleDAO = gameRiseDatabase.battleDAO();
    }

    @Override
    public void getBattles(String tagId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> battleLogCallback.onSuccessFromLocal(battleDAO.getAll(tagId)));
    }

    @Override
    //al posto della lista, potrebbe arrivarci una battaglia alla volta
    public void insertBattles(List<Battle> battleList, String tagId) {
        //TODO: potrebbe esserci qualche errore su "battleDAO.insertAll(toInsertOrUpdate);"
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            List<Battle> allBattles = battleDAO.getAll(tagId);
            List<Battle> toInsertOrUpdate = new ArrayList<>();
            if(allBattles != null){
                for(Battle newBattle : battleList){
                    if(!allBattles.contains(newBattle))
                        toInsertOrUpdate.add(newBattle);
                }

                if (!toInsertOrUpdate.isEmpty()) {
                    battleDAO.insertAll(toInsertOrUpdate);
                }
                battleLogCallback.onSuccessFromLocal(battleDAO.getAll(tagId));
            }else battleDAO.insertAll(battleList);

        });
    }
}
