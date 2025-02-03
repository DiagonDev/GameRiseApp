package xyz.brawl.gamerise.ui.viewmodels.brawlers;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import xyz.brawl.gamerise.model.repository.brawler.BrawlersRepository;

public class BrawlersViewModelFactory implements ViewModelProvider.Factory {
    private final BrawlersRepository brawlerRepository;

    public BrawlersViewModelFactory(BrawlersRepository brawlerRepository) {
        this.brawlerRepository = brawlerRepository;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BrawlersViewModel(brawlerRepository);
    }
}
