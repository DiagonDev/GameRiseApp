package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;

public class BrawlersViewModel extends ViewModel {
    /*private final MutableLiveData<BrawlerEntry> selectedBrawler = new MutableLiveData<>();

    public void selectBrawler(BrawlerEntry brawler) {
        selectedBrawler.setValue(brawler);
    }

    public LiveData<BrawlerEntry> getSelectedBrawler() {
        return selectedBrawler;
    }*/

    private MutableLiveData<Result> brawlersLiveData;
    private final BrawlersRepository brawlersRepository;

    public BrawlersViewModel(BrawlersRepository brawlersRepository) {
        this.brawlersRepository = brawlersRepository;
    }

    public MutableLiveData<Result> getBrawlers(String tag, long lastUpdate) {
        if(brawlersLiveData == null){
            fetchBrawlers(tag, lastUpdate);
        }
        return brawlersLiveData;
    }

    private void fetchBrawlers(String tag, long lastUpdate) {
        brawlersLiveData = brawlersRepository.fetchBrawlers(tag, lastUpdate);
    }
}