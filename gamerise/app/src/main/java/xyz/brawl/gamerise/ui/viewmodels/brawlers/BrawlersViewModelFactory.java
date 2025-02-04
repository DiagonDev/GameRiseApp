package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.model.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.model.repository.starpower.StarPowerRepository;

public class BrawlersViewModelFactory implements ViewModelProvider.Factory {
    private final BrawlersRepository brawlerRepository;
    private final StarPowerRepository starPowerRepository;
    private final GadgetRepository gadgetRepository;


    public BrawlersViewModelFactory(BrawlersRepository brawlerRepository, StarPowerRepository starPowerRepository, GadgetRepository gadgetRepository) {
        this.brawlerRepository = brawlerRepository;
        this.starPowerRepository = starPowerRepository;
        this.gadgetRepository = gadgetRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BrawlersViewModel(brawlerRepository, starPowerRepository, gadgetRepository);
    }
}
