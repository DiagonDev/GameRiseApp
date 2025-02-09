package xyz.brawl.gamerise.model.data.datasource.gadget;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.repository.gadget.GadgetCallback;

public abstract class BaseGadgetLocalDataSource {
    protected GadgetCallback gadgetCallback;

    public void setGadgetCallback(GadgetCallback gadgetCallback) {
        this.gadgetCallback = gadgetCallback;
    }

    public abstract void getGadgets(Long brawlerId);

    public abstract void insertGadgets(List<GadgetEntry> gadgetEntryList);

}
