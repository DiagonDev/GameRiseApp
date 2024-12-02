package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.data.brawler.Brawler;

public class BrawlersViewModel extends ViewModel {
    private final MutableLiveData<Brawler> selectedBrawler = new MutableLiveData<>();

    public void selectBrawler(Brawler brawler) {
        selectedBrawler.setValue(brawler);
    }

    public LiveData<Brawler> getSelectedBrawler() {
        return selectedBrawler;
    }
}