package xyz.brawl.gamerise.repository.player;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.source.player.BasePlayerRemoteDataSource;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;
import xyz.brawl.gamerise.repository.brawler.BrawlersRepository;
import xyz.brawl.gamerise.repository.gadget.GadgetRepository;
import xyz.brawl.gamerise.repository.starpower.StarPowerRepository;
import xyz.brawl.gamerise.repository.stats.StatsRepository;
import xyz.brawl.gamerise.repository.tag.TagRepository;

public class PlayerRepository implements PlayerCallBack {
    private final MutableLiveData<Result> playerLiveData;
    private final BasePlayerRemoteDataSource playerRemoteDataSource;
    private final TagRepository tagRepository;
    private final BrawlersRepository brawlersRepository;
    private final StatsRepository statsRepository;
    private final StarPowerRepository starPowerRepository;
    private final GadgetRepository gadgetRepository;

    public PlayerRepository(
            BasePlayerRemoteDataSource playerRemoteDataSource,
            TagRepository tagRepository,
            BrawlersRepository brawlersRepository,
            StatsRepository statsRepository,
            StarPowerRepository starPowerRepository,
            GadgetRepository gadgetRepository) {
        playerLiveData = new MutableLiveData<>();
        this.playerRemoteDataSource = playerRemoteDataSource;
        this.playerRemoteDataSource.setPlayerCallBack(this);

        this.tagRepository = tagRepository;
        this.brawlersRepository = brawlersRepository;
        this.statsRepository = statsRepository;
        this.starPowerRepository = starPowerRepository;
        this.gadgetRepository = gadgetRepository;
    }

    public MutableLiveData<Result> fetchPlayer(String tagId) {
        playerRemoteDataSource.getPlayer(tagId);
        return playerLiveData;
    }

    @Override
    public void onSuccessFromRemote(PlayerApiResponse playerApiResponse, long lastUpdate) {
        //  Quando l'API restituisce una risposta con successo, salvo i dati nei repository locali
        if (playerApiResponse != null) {
            // Salva i dati nelle repository specifiche
            brawlersRepository.insertBrawlers(PlayerMapper.mapToBrawlers(playerApiResponse));
            statsRepository.insertStats(PlayerMapper.mapToStat(playerApiResponse));
            starPowerRepository.insertStarPowers(PlayerMapper.mapToStarPowers(playerApiResponse));
            gadgetRepository.insertGadgets(PlayerMapper.mapToGadgets(playerApiResponse));
            // Notifica il LiveData che i dati sono stati aggiornati
            playerLiveData.postValue(new Result.Success(playerApiResponse));
        }
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error result = new Result.Error(exception.getMessage());
        playerLiveData.postValue(result);
    }
}
