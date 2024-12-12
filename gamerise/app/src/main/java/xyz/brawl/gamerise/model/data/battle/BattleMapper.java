package xyz.brawl.gamerise.model.data.battle;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.battle.api.*;
import xyz.brawl.gamerise.util.Constants;

public class BattleMapper {
    public static List<Battle> mapToBattles(BattleLogApiResponse apiResponse) {
        List<Battle> battles = new ArrayList<>();

        for (BattleLogEntry battleLogEntry : apiResponse.getBattleResponseList()) {
            Battle battle = new Battle.BattleBuilder()
                    .title(battleLogEntry.getEvent().getMode())
                    .subTitle(battleLogEntry.getEvent().getMap())
                    .trophies(String.valueOf(battleLogEntry.getBattle().getTrophyChange()))
                    .iconMode(Constants.GameMode.valueOf(battleLogEntry.getEvent().getMode()).getIconModeid())
                    .iconRanked(battleLogEntry.getBattle().getType().equals("ranked")? Constants.iconRanked : Constants.iconStandard)
                    .build();
        }
        return battles;
    }
}
