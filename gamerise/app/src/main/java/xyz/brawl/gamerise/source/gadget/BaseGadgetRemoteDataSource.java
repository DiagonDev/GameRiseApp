package xyz.brawl.gamerise.source.gadget;

import xyz.brawl.gamerise.repository.gadget.GadgetCallback;

public abstract class BaseGadgetRemoteDataSource {
    protected GadgetCallback gadgetCallback;
    public void setGadgetCallback(GadgetCallback gadgetCallback) {
        this.gadgetCallback = gadgetCallback;
    }
    public abstract void getGadgetList(String tagId);
}
