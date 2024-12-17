package xyz.brawl.gamerise.model.data.player;

import java.util.ArrayList;
import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.util.Constants;

public class PlayerMapper {
    public static Stat mapToStat(PlayerApiResponse playerApiResponse) {
        return new Stat.StatBuilder()
                .tag(playerApiResponse.tag)
                ._3vs3Victories(playerApiResponse._3vs3Victories)
                .trophies(playerApiResponse.trophies)
                .expLevel(playerApiResponse.expLevel)
                .club(playerApiResponse.club)
                .highestTrophies(playerApiResponse.highestTrophies)
                .rank(playerApiResponse.rank)
                .soloVictories(playerApiResponse.soloVictories)
                .duoVictories(playerApiResponse.duoVictories)
                .build();
    }

    public static List<BrawlerEntry> mapToBrawlers(PlayerApiResponse playerApiResponse) {
        List<BrawlerEntry> brawlersList = new ArrayList<>();
        for (BrawlerEntry brawler : playerApiResponse.brawlers) {
            brawler.setBrawlerPin(Constants.BrawlerPin.fromBrawlerPinString(brawler.getName()).getIconPlayerId());
            brawlersList.add(brawler);
        }
        return brawlersList;
    }
    //TODO: creare appena serve
    public static List<GadgetEntry> mapToGadgets(PlayerApiResponse playerApiResponse) {
        return null;
    }
    //TODO: creare appena serve
    public static List<StarPowerEntry> mapToStarPowers(PlayerApiResponse playerApiResponse) {
        return null;
    }
}
