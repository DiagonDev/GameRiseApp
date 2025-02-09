package xyz.brawl.gamerise.util;


import xyz.brawl.gamerise.model.repository.battlelog.BattleLogRepository;
import xyz.brawl.gamerise.model.repository.player.PlayerRepository;

/**
 * Applicazione pattern Façade
 * Questa classe incapsula tutti i metodi relativi alla logica di fetch da API e di salvataggio nel database
 * Verrà chiamata dai ViewModel e userà i metodi dei repository
 */
public class DownloadDataFacade  {
    private final PlayerRepository playerRepository;
    private final BattleLogRepository battleLogRepository;

    public DownloadDataFacade(PlayerRepository playerRepository, BattleLogRepository battleLogRepository) {
        this.playerRepository = playerRepository;
        this.battleLogRepository = battleLogRepository;
    }

    /**
     * Step 1: Esegue fetch del player per il tag corrente
     * Step 2: Esegue fetch della battlelog per il tag corrente
     */

    public void saveData(String tag, boolean save) {
        int temp = 100000;
        playerRepository.fetchPlayer(tag, temp);
        battleLogRepository.fetchBattleLog(tag, temp);
    }
}

