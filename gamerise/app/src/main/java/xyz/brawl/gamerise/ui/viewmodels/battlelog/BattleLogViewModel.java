package xyz.brawl.gamerise.ui.viewmodels.battlelog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;

public class BattleLogViewModel extends ViewModel {
    private final MutableLiveData<List<Battle>> battlesLiveData = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Battle>> getBattles() {
        return battlesLiveData;
    }

    public void addBattle(Battle battle) {
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
    }
}