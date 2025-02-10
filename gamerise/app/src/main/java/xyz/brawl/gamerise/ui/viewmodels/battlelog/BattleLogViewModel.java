package xyz.brawl.gamerise.ui.viewmodels.battlelog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;

public class BattleLogViewModel extends ViewModel {
    private MutableLiveData<Result> battlesLiveData;
    private final BattleLogRepository battleLogRepository;

    public BattleLogViewModel(BattleLogRepository battleLogRepository) {
        this.battleLogRepository = battleLogRepository;
    }

    public MutableLiveData<Result> getBattles(String tag, boolean connected, long lastUpdate) {
        if (battlesLiveData == null) {
            fetchBattleLog(tag, connected, lastUpdate);
        }
        return battlesLiveData;
    }

    private void fetchBattleLog(String tag, boolean connected, long lastUpdate) {
        battlesLiveData = battleLogRepository.fetchBattleLog(tag, connected, lastUpdate);
    }
}