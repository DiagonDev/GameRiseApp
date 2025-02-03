package xyz.brawl.gamerise.ui.viewmodels.battlelog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;

public class BattleLogViewModel extends ViewModel {
    //private final MutableLiveData<List<Battle>> battlesLiveData = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<Result> battlesLiveData;
    private final BattleLogRepository battleLogRepository;
    /*public LiveData<List<Battle>> getBattles() {
        return battlesLiveData;
    }*/

    public BattleLogViewModel(BattleLogRepository battleLogRepository) {
        this.battleLogRepository = battleLogRepository;
    }

    public MutableLiveData<Result> getBattles(String tag, long lastUpdate) {
        if(battlesLiveData == null){
            fetchBattleLog(tag, lastUpdate);
        }
        return battlesLiveData;
    }

    private void fetchBattleLog(String tag, long lastUpdate) {
        battlesLiveData = battleLogRepository.fetchBattleLog(tag, lastUpdate);
    }

    /*public void addBattle(Battle battle) {
        List<Battle> currentBattles = battlesLiveData.getValue();
        if (currentBattles != null) {
            List<Battle> updatedBattles = new ArrayList<>(currentBattles);
            updatedBattles.add(battle);
            battlesLiveData.setValue(updatedBattles);
        }
    }

    public void removeBattle(Battle battle) {
        List<Battle> currentBattles = battlesLiveData.getValue();
        if (currentBattles != null) {
            List<Battle> updatedBattles = new ArrayList<>(currentBattles);
            updatedBattles.remove(battle);
            battlesLiveData.setValue(updatedBattles);
        }
    }*/
}