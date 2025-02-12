package xyz.brawl.gamerise.repository.player;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.brawler.StarPowerEntry;
import xyz.brawl.gamerise.source.player.BasePlayerLocalDataSource;
import xyz.brawl.gamerise.source.player.BasePlayerRemoteDataSource;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.source.player.PlayerLocalDataSource;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;
import xyz.brawl.gamerise.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.repository.stats.StatsRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;
import java.util.concurrent.Executors;


public class PlayerRepository implements PlayerCallBack {
    private final MutableLiveData<Result> playerLiveData;
    private final BasePlayerRemoteDataSource playerRemoteDataSource;
    private final BasePlayerLocalDataSource playerLocalDataSource;
    private final StatsRepository statsRepository;
    private final StarPowerRepository starPowerRepository;
    private final GadgetRepository gadgetRepository;
    private final BrawlersRepository brawlersRepository;

    public PlayerRepository(
            BasePlayerRemoteDataSource playerRemoteDataSource,
            StatsRepository statsRepository,
            BasePlayerLocalDataSource playerLocalDataSource, StarPowerRepository starPowerRepository, GadgetRepository gadgetRepository, BrawlersRepository brawlersRepository) {
        playerLiveData = new MutableLiveData<>();
        this.playerRemoteDataSource = playerRemoteDataSource;
        this.playerRemoteDataSource.setPlayerCallBack(this);
        this.playerLocalDataSource = playerLocalDataSource;
        this.statsRepository = statsRepository;
        this.starPowerRepository = starPowerRepository;
        this.gadgetRepository = gadgetRepository;
        this.brawlersRepository = brawlersRepository;
    }

    public MutableLiveData<Result> fetchPlayer(String tagId, boolean connected) {
        if (connected)
            playerRemoteDataSource.getPlayer(tagId);
        return playerLiveData;
    }

    @Override
    public void onSuccessFromRemote(PlayerApiResponse playerApiResponse, long lastUpdate) {
        //  Quando l'API restituisce una risposta con successo, salvo i dati nei repository locali
        if (playerApiResponse != null) {
            // Salva i dati nelle repository specifiche
            statsRepository.insertStats(PlayerMapper.mapToStat(playerApiResponse));
            List<BrawlerEntry> brawlers = PlayerMapper.mapToBrawlers(playerApiResponse);
            List<StarPowerEntry> starPowers = PlayerMapper.mapToStarPowers(playerApiResponse);
            List<GadgetEntry> gadgets = PlayerMapper.mapToGadgets(playerApiResponse);
            Executors.newSingleThreadExecutor().execute(() -> {
                playerLocalDataSource.insertPlayerData(brawlers, starPowers, gadgets);
                playerLiveData.postValue(new Result.Success(playerApiResponse));
            });
        }
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error result = new Result.Error(exception.getMessage());
        playerLiveData.postValue(result);
    }

    @Override
    public void onSuccessFromLocal(List<BrawlerEntry> brawler, List<StarPowerEntry> starPower, List<GadgetEntry> gadget) {
        Result result = new Result.Success(brawler);
        playerLiveData.postValue(result);
    }
}
