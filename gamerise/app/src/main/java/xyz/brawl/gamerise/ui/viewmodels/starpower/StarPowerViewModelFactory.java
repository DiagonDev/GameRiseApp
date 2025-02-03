package xyz.brawl.gamerise.ui.viewmodels.starpower;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;

public class StarPowerViewModelFactory implements ViewModelProvider.Factory {
    private final StarPowerRepository starPowerRepository;

    public StarPowerViewModelFactory(StarPowerRepository starPowerRepository) {
        this.starPowerRepository = starPowerRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StarPowerViewModel(starPowerRepository);
    }
}
