package xyz.brawl.gamerise.model.repository.gadget;

import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;

public interface GadgetCallback {
    void onSuccessFromRemote(List<GadgetEntry> gadgetList, long lastUpdate);
    void onFailureFromRemote(Exception errorMessage);

    void onSuccessFromLocal(List<GadgetEntry> gadgetList);
}
