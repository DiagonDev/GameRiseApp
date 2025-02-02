package xyz.brawl.gamerise.model.data.battle;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.battle.api.BattleEntry;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.battle.api.EventEntry;
import xyz.brawl.gamerise.model.data.battle.api.PlayerEntry;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.util.Constants;

public class BattleMapper {
    public static List<Battle> mapToBattles(List<BattleLogEntry> battleLogEntryList) {
        List<Battle> battles = new ArrayList<>();
        //TODO: prima porzione di codice da controllare se ci sono problemi :)
        for (BattleLogEntry battleLogEntry : battleLogEntryList) {
            String[] playersBrawler = Constants.getPlayersBrawler(battleLogEntry);
            Battle battle = new Battle.BattleBuilder()
                    .title(battleLogEntry.getEvent().getMode())
                    .subTitle(battleLogEntry.getEvent().getMap())
                    .trophies(String.valueOf(battleLogEntry.getBattle().getTrophyChange()))
                    .iconMode(Constants.GameMode.fromModeString(battleLogEntry.getEvent().getMode()).getIconModeid())
                    .iconRanked(battleLogEntry.getBattle().getType().equals("ranked") ? Constants.iconStandard : Constants.iconRanked)
                    .iconPlayer1(playersBrawler[0] == null ? Constants.iconStandard :
                            Constants.BrawlerPin.fromBrawlerPinString(playersBrawler[0]).getIconPlayerId())
                    .iconPlayer2(playersBrawler[1] == null ? Constants.iconStandard :
                            Constants.BrawlerPin.fromBrawlerPinString(playersBrawler[1]).getIconPlayerId())
                    .iconPlayer3(playersBrawler[2] == null ? Constants.iconStandard :
                            Constants.BrawlerPin.fromBrawlerPinString(playersBrawler[2]).getIconPlayerId())
                    .battleId(battleLogEntry.getBattleTime())
                    .build();
            battles.add(battle);
        }
        return battles;
    }

    //Potrebbe essere sbagliato
    public static List<BattleLogEntry> mapToBattleLogEntries(List<Battle> battleList) {
        List<BattleLogEntry> battleLogEntries = new ArrayList<>();

        for (Battle battle : battleList) {
            BattleLogEntry battleLogEntry = new BattleLogEntry();

            // Creazione di EventEntry (che rappresenta la modalità e la mappa)
            EventEntry eventEntry = new EventEntry();
            eventEntry.setMode(battle.title); // Il titolo della Battle è la modalità di gioco
            eventEntry.setMap(battle.subTitle); // Il sottotitolo è la mappa

            // Creazione di BattleEntry
            BattleEntry battleEntry = new BattleEntry();
            battleEntry.setTrophyChange(Integer.parseInt(battle.trophies)); // Converti le coppe in intero

            // Determina il tipo di battaglia (ranked o standard)
            battleEntry.setType(battle.iconRanked == Constants.iconRanked ? "ranked" : "standard");

            // Creazione della lista di PlayerEntry per i giocatori
            List<PlayerEntry> playerEntries = new ArrayList<>();
            String[] playersBrawler = {null, null, null};

            playersBrawler[0] = Constants.BrawlerPin.fromIconPlayerId(battle.iconPlayer1).getBrawlerPinString();
            playersBrawler[1] = Constants.BrawlerPin.fromIconPlayerId(battle.iconPlayer2).getBrawlerPinString();
            playersBrawler[2] = Constants.BrawlerPin.fromIconPlayerId(battle.iconPlayer3).getBrawlerPinString();

            for (String brawlerName : playersBrawler) {
                if (brawlerName != null) {
                    PlayerEntry playerEntry = new PlayerEntry();
                    BrawlerEntry brawlerEntry = new BrawlerEntry();
                    brawlerEntry.setName(brawlerName);
                    playerEntry.setBrawler(brawlerEntry);
                    playerEntries.add(playerEntry);
                }
            }

            battleEntry.setPlayers(playerEntries);

            // Impostiamo i dati nel BattleLogEntry
            battleLogEntry.setEvent(eventEntry);
            battleLogEntry.setBattle(battleEntry);
            battleLogEntry.setBattleTime(battle.battleId); // Usiamo battleId come battleTime

            // Aggiungiamo alla lista
            battleLogEntries.add(battleLogEntry);
        }

        return battleLogEntries;
    }


}
