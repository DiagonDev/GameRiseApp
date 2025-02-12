package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.repository.starpower.StarPowerRepository;

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

    public MutableLiveData<Result> getBrawlers(String tag, boolean connected) {
        if(brawlersLiveData == null){
            brawlersLiveData = brawlersRepository.fetchBrawlers(tag,connected);
        }
        return brawlersLiveData;
    }
    public MutableLiveData<Result> getStarPower(Long brawlerId, boolean connected, String tagId) {
        return starPowerRepository.fetchStarPower(brawlerId, connected, tagId);
    }

    public MutableLiveData<Result> getGadgets(Long brawlerId, boolean connected, String tagId) {
        return gadgetRepository.fetchGadget(brawlerId, connected, tagId);
    }
}