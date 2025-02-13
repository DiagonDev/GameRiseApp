package xyz.brawl.gamerise.viewmodels.stats;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.repository.stats.StatsRepository;

public class StatsViewModel extends ViewModel {
    private MutableLiveData<Result> statsLiveData;
    private final StatsRepository statsRepository;

    public StatsViewModel(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public MutableLiveData<Result> getStats(String tag, boolean connected) {
        if (statsLiveData == null) {
            fetchStats(tag, connected);
        }
        return statsLiveData;
    }

    private void fetchStats(String tag, boolean connected) {
        statsLiveData = statsRepository.fetchStats(tag, connected);
    }
}