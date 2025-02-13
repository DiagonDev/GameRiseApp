<<<<<<< Updated upstream
package xyz.brawl.gamerise.source.starPower;
=======
package xyz.brawl.gamerise.source.starpower;
>>>>>>> Stashed changes

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.StarPowerDAO;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class StarPowerLocalDataSource extends  BaseStarPowerLocalDataSource{
    private final StarPowerDAO starPowerDAO;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public StarPowerLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.starPowerDAO = gameRiseDatabase.starPowerDAO();
    }

    @Override
    public void getStarPower(Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> starPowerCallback.onSuccessFromLocal(starPowerDAO.getAll(brawlerId)));
    }
}
