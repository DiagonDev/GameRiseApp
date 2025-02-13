package xyz.brawl.gamerise.ui.viewmodels.battlelog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import xyz.brawl.gamerise.repository.battlelog.BattleLogRepository;

public class BattleLogViewModelFactory implements ViewModelProvider.Factory {
    private final BattleLogRepository battleLogRepository;

    public BattleLogViewModelFactory(BattleLogRepository battleLogRepository) {
        this.battleLogRepository = battleLogRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BattleLogViewModel(battleLogRepository);
    }
}
