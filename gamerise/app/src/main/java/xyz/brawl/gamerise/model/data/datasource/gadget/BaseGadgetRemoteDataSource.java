package xyz.brawl.gamerise.model.data.datasource.gadget;

import xyz.brawl.gamerise.model.repository.gadget.GadgetCallback;

public abstract class BaseGadgetRemoteDataSource {
    protected GadgetCallback gadgetCallback;
    public void setGadgetCallback(GadgetCallback gadgetCallback) {
        this.gadgetCallback = gadgetCallback;
    }
    public abstract void getGadgetList(String tagId);
}
