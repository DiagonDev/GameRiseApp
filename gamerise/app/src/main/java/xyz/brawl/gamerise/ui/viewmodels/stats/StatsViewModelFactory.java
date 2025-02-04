package xyz.brawl.gamerise.ui.viewmodels.stats;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.stats.StatsRepository;
import xyz.brawl.gamerise.ui.viewmodels.battlelog.BattleLogViewModel;

public class StatsViewModelFactory implements ViewModelProvider.Factory{

    private final StatsRepository statsRepository;

    public StatsViewModelFactory(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StatsViewModel(statsRepository);
    }


}
