package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.model.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;

public class BrawlersViewModel extends ViewModel {
    private MutableLiveData<Result> brawlersLiveData;
    private final BrawlersRepository brawlersRepository;
    private final StarPowerRepository starPowerRepository;
    private final GadgetRepository gadgetRepository;

    public BrawlersViewModel(BrawlersRepository brawlersRepository, StarPowerRepository starPowerRepository, GadgetRepository gadgetRepository) {
        this.brawlersRepository = brawlersRepository;
        this.starPowerRepository = starPowerRepository;
        this.gadgetRepository = gadgetRepository;
    }

    public MutableLiveData<Result> getBrawlers(String tag, long lastUpdate) {
        if(brawlersLiveData == null){
            brawlersLiveData = brawlersRepository.fetchBrawlers(tag, lastUpdate);
        }
        return brawlersLiveData;
    }
    public MutableLiveData<Result> getStarPower(Long brawlerId, long lastUpdate) {
        return starPowerRepository.fetchStarPower(brawlerId, lastUpdate);
    }

    public MutableLiveData<Result> getGadgets(Long brawlerId, long lastUpdate) {
        return gadgetRepository.fetchGadget(brawlerId, lastUpdate);
    }
}