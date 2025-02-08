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
    
    public static List<GadgetEntry> mapToGadgets(PlayerApiResponse playerApiResponse) {
        List<GadgetEntry> gadgetList = new ArrayList<>();

        for (BrawlerEntry brawler : playerApiResponse.brawlers) {
            if (brawler.getGadgetEntries() != null) {
                for (GadgetEntry gadget : brawler.getGadgetEntries()) {
                    gadget.setBrawlerId(brawler.getId()); // Imposta l'ID del brawler di riferimento
                    gadget.setGadgetPin(Constants.GadgetPin.fromGadgetPinString(gadget.getName()).getIconGadgetId());
                    gadgetList.add(gadget);
                }
            }
        }

        return gadgetList;
    }

    public static List<StarPowerEntry> mapToStarPowers(PlayerApiResponse playerApiResponse) {
        List<StarPowerEntry> starPowerList = new ArrayList<>();

        for (BrawlerEntry brawler : playerApiResponse.brawlers) {
            if (brawler.getStarPowersEntries() != null) {
                for (StarPowerEntry starPower : brawler.getStarPowersEntries()) {
                    starPower.setBrawlerId(brawler.getId()); // Imposta l'ID del brawler di riferimento
                    starPower.setStarPowerPin(Constants.StarPowerPin.fromStarPowerPinString(starPower.getName()).getIconStarPowerId());
                    starPowerList.add(starPower);
                }
            }
        }

        return starPowerList;
    }


}
