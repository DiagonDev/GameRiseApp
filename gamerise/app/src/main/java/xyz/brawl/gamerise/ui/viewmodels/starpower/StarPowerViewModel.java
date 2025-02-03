package xyz.brawl.gamerise.ui.viewmodels.starpower;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;

public class StarPowerViewModel extends ViewModel {
    private final StarPowerRepository starPowerRepository;
    private MutableLiveData<Result> starPowerLiveData;

    public StarPowerViewModel(StarPowerRepository starPowerRepository) {
        this.starPowerRepository = starPowerRepository;
    }

    public MutableLiveData<Result> getStarPower(Long brawlerId, long lastUpdate) {
        if(starPowerLiveData == null){
            fetchStarPower(brawlerId, lastUpdate);
        }
        return starPowerLiveData;
    }

    private void fetchStarPower(Long brawlerId, long lastUpdate) {
        starPowerLiveData = starPowerRepository.fetchStarPower(brawlerId, lastUpdate);
    }
}
