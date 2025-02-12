package xyz.brawl.gamerise.source.gadget;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.repository.gadget.GadgetCallback;

public abstract class BaseGadgetLocalDataSource {
    protected GadgetCallback gadgetCallback;

    public void setGadgetCallback(GadgetCallback gadgetCallback) {
        this.gadgetCallback = gadgetCallback;
    }

    public abstract void getGadgets(Long brawlerId);

    public abstract void insertGadgets(List<GadgetEntry> gadgetEntryList);

}
