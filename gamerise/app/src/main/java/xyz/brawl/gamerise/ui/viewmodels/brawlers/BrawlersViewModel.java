package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;

public class BrawlersViewModel extends ViewModel {
    private final MutableLiveData<BrawlerEntry> selectedBrawler = new MutableLiveData<>();

    public void selectBrawler(BrawlerEntry brawler) {
        selectedBrawler.setValue(brawler);
    }

    public LiveData<BrawlerEntry> getSelectedBrawler() {
        return selectedBrawler;
    }
}