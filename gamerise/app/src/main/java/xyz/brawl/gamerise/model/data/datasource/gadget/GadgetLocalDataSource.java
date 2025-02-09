package xyz.brawl.gamerise.model.data.datasource.gadget;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.GadgetDAO;
import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;

public class GadgetLocalDataSource  extends BaseGadgetLocalDataSource{
    private final GadgetDAO gadgetDAO;

    public GadgetLocalDataSource(GameRiseDatabase gameRiseDatabase) {
        this.gadgetDAO = gameRiseDatabase.gadgetDAO();
    }
    @Override
    public void getGadgets(Long brawlerId) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> gadgetCallback.onSuccessFromLocal(gadgetDAO.getAll(brawlerId)));
    }

    @Override
    public void insertGadgets(List<GadgetEntry> gadgetEntryList) {
        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
            gadgetDAO.insertAll(gadgetEntryList);
            gadgetCallback.onSuccessFromLocal(gadgetEntryList);
        });
    }
}
