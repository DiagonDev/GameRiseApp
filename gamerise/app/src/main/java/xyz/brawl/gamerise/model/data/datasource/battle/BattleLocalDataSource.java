package xyz.brawl.gamerise.model.data.datasource.battle;

import java.util.ArrayList;
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
            if(battle != null) {
                if(allBattles.contains(battle)){
                    battleDAO.delete(battle);
                    battleLogCallback.onSuccessFromLocal(battleDAO.getAll());
                    //allBattles.remove(battle);
                }else battleLogCallback.onFailureFromLocal(new Exception("Battle not found"));
            } else battleLogCallback.onFailureFromLocal(new Exception("Battle not found"));
            // al posto della frase, si può creare una costante e mandargli quella
        });
    }

    @Override
    //al posto della lista, potrebbe arrivarci una battaglia alla volta
    public void insertBattles(List<Battle> battleList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            List<Battle> allBattles = battleDAO.getAll();
            List<Battle> toInsertOrUpdate = new ArrayList<>();
            if(battleList != null){
                for(Battle newBattle : battleList){
                    if(!allBattles.contains(newBattle))
                        toInsertOrUpdate.add(newBattle);
                }
            }
            if (!toInsertOrUpdate.isEmpty()) {
                battleDAO.insertAll(toInsertOrUpdate);
            }
            battleLogCallback.onSuccessFromLocal(battleDAO.getAll());
        });
    }
}
