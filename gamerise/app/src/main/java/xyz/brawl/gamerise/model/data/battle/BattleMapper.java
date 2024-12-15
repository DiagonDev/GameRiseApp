package xyz.brawl.gamerise.model.data.battle;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.*;
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
                    .build();
            battles.add(battle);
        }
        return battles;
    }
    public void handleException(){

    }
}
