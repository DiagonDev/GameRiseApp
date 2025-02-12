package xyz.brawl.gamerise.repository.gadget;

import java.util.List;

import xyz.brawl.gamerise.model.brawler.GadgetEntry;

public interface GadgetCallback {
    void onSuccessFromRemote(List<GadgetEntry>  gadget);
    void onFailureFromRemote(Exception errorMessage);

    void onSuccessFromLocal(List<GadgetEntry> gadgetList);
}
