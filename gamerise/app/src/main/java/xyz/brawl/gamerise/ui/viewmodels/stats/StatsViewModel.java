package xyz.brawl.gamerise.ui.viewmodels.stats;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.stats.StatsRepository;

public class StatsViewModel extends ViewModel {
    private MutableLiveData<Result> statsLiveData;
    private final StatsRepository statsRepository;

    public StatsViewModel(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public MutableLiveData<Result> getBattles(String tag, long lastUpdate) {
        if (statsLiveData == null) {
            fetchBattleLog(tag, lastUpdate);
        }
        return statsLiveData;
    }

    private void fetchBattleLog(String tag, long lastUpdate) {
        statsLiveData = statsRepository.fetchStats(tag, lastUpdate);
    }
}