package xyz.brawl.gamerise.source.gadget;

import xyz.brawl.gamerise.database.GadgetDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.TagDAO;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class GadgetLocalDataSource  extends BaseGadgetLocalDataSource{
    private final GadgetDAO gadgetDAO;
    private final TagDAO tagDao;
    private final GameAccountSingleton gameAccountSingleton = GameAccountSingleton.getInstance();

    public GadgetLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.gadgetDAO = gameRiseDatabase.gadgetDAO();
        this.tagDao = gameRiseDatabase.tagDAO();
    }
    @Override
    public void getGadgets(Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> gadgetCallback.onSuccessFromLocal(gadgetDAO.getAll(brawlerId)));
    }
}
