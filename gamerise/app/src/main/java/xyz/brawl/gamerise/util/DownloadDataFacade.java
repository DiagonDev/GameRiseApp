package xyz.brawl.gamerise.util;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.database.GameRiseDatabase;
import xyz.brawl.gamerise.database.OwnsEntity;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.BattleMapper;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;
import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.battlelog.IBattleLogRepository;
import xyz.brawl.gamerise.model.repository.player.IPlayerRepository;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;
import xyz.brawl.gamerise.model.service.ServiceLocator;

/**
 * Applicazione pattern Façade
 * Questa classe incapsula tutti i metodi relativi alla logica di fetch da API e di salvataggio nel database
 * Verrà chiamata dai ViewModel e userà i metodi dei repository
 * TODO: tutto il codice presente in questa classe verrà spostato ove corretto qui dentro avremo solo i metodi
 */
public class DownloadDataFacade implements ResponseCallback {
    private final Application application;
    private final GameRiseDatabase database;
    private IBattleLogRepository battleLogRepository;
    private IPlayerRepository playerRepository;

    public DownloadDataFacade(Application application) {
        this.application = application;
        database = ServiceLocator.getInstance().getDatabase(application);
    }

    /**
     * Step 1: Salva il tag nel database se non presente, altrimenti lo sposta in cima
     * Step 2: Esegue fetch della battlelog per il tag corrente
     * Step 3: La fetch chiama il metodi del responseCallback per salvare i dati nel database
     * Step 4: Eseguire fetch del player per il tag corrente
     * Step 5: come Step 3 ma con le stats, brawlers, gadget e starpowers
     * Step 6: Carico la tabella Owns nel database
     **/
    public void downloadAndSaveData(Tag tag) {
        /// Step 1
        if (database != null) {
            GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                // Controlla se il tag esiste già nel database
                if (database.tagDao().findTagByName(tag.getTag()) != null) {
                    // Rimuovi il tag esistente dalla vecchia posizione
                    database.tagDao().delete(tag);
                }
                // Inserisci il nuovo tag nel database
                database.tagDao().insertAll(tag);

                // Controlla se ci sono più di 3 tag nel database
                List<Tag> allTags = database.tagDao().getRecentTags();
                if (allTags.size() > 3) {
                    // Rimuovi il tag più vecchio
                    Tag oldestTag = allTags.get(allTags.size() - 1); // Ultimo nella lista
                    database.tagDao().delete(oldestTag);
                    allTags.remove(oldestTag);
                }
            });
            /// Step 2
            battleLogRepository = new BattleLogRepository(application, this);
            battleLogRepository.fetchBattleLog(Constants.tagTeo);

            /// Step 4
            playerRepository = new PlayerRepository(application, this);
            playerRepository.fetchPlayer(Constants.tagTeo);
        }

    }

    //TODO: implementare altre fetchApi e ritornare il Battleid
    @Override
    public void onSuccess(Object o, long lastUpdate) {
        if (database.tagDao().findTagByName(GameAccountSingleton.getInstance().getUserTag()) != null) {
            /// Step 3
            if (o instanceof List) {
                if (((List<?>) o).get(0) instanceof BattleLogEntry) {
                    /* Ho bisogno di creare una lista di Battle perchè il mapping di mapToBattles mi crea le battaglie a livello di dominio
                     * ma l'entity del database ha bisogno anche del tag nella lista, questa differenziazione è un effetto collaterale di
                     * tenere una classe unica polivalente
                     */
                    @SuppressWarnings("unchecked") // Se entra nell'if allora è List<BattleLogEntry>, serve al compilatore
                    List<Battle> battles = BattleMapper.mapToBattles((List<BattleLogEntry>) o);

                    List<Battle> battleToAdd = new ArrayList<>();
                    for (Battle battle : battles) {
                        if (database.battleDAO().findBattleByBattleId(battle.battleId) == null) {
                            battle.setTagId(GameAccountSingleton.getInstance().getUserTag());
                            battleToAdd.add(battle);
                        }
                    }


                    // Eseguiamo l'operazione di scrittura in background
                    GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                        database.battleDAO().insertAll(battleToAdd);
                    });
                }
            }
            /// Step 5
            if (o instanceof PlayerApiResponse) {
                PlayerApiResponse playerApiResponse = (PlayerApiResponse) o;
                Stat stat = PlayerMapper.mapToStat(playerApiResponse);
                List<BrawlerEntry> brawlers = PlayerMapper.mapToBrawlers(playerApiResponse);
                if (database.statDao().findStatByName(stat.getTag()) == null) {
                    // Eseguiamo l'operazione di scrittura in background
                    GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                        /* SOLO PER DEBUG
                         * Questo perchè la query la sto facendo di default su teo, nel PlayerMapper io associo a stat
                         * la tag che ottengo dalla chiamata api (quindi teo)
                         * solo che la tag che inserisco a mano è "disabilitata
                         * togliere la riga sottostante finita la fase di sviluppo
                         */
                        stat.setTag(GameAccountSingleton.getInstance().getUserTag());
                        database.statDao().insert(stat);
                    });
                }
                for (BrawlerEntry brawler : brawlers) {
                    if (database.brawlerDAO().findBrawlerById(brawler.getId()) == null) {
                        GameRiseDatabase.databaseWriteExecutor.execute(() -> {
                            /// Inserisco il brawler
                            database.brawlerDAO().insert(brawler);
                            if (brawler.getGadgetEntries() != null) {
                                /// Inserisco i Gadget del brawler
                                for (GadgetEntry gadget : brawler.getGadgetEntries()) {
                                    gadget.setBrawlerId(brawler.getId());
                                    database.gadgetDAO().insert(gadget);
                                }
                            }
                            if (brawler.getStarPowersEntries() != null) {
                                /// Inserisco gli StarPower del brawler
                                for (StarPowerEntry starPowerEntry : brawler.getStarPowersEntries()) {
                                    starPowerEntry.setBrawlerId(brawler.getId());
                                    database.starPowerDAO().insert(starPowerEntry);
                                }
                            }
                            database.ownsDAO().insert(new OwnsEntity(
                                    GameAccountSingleton.getInstance().getUserTag(),
                                    brawler.getId()
                            ));
                        });
                    }

                }
            }
        }

    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
