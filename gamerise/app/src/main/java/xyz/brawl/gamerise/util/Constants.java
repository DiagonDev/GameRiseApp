package xyz.brawl.gamerise.util;

import java.util.List;

import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.brawler.Brawler;

public class Constants {

    public static final String API_ENDPOINT_URL = "https://sk8.fun:5223/";

    public enum GameMode{
        GEMGRAB,
        HEIST,
        BOUNTY,
        BRAWLBALL,
        SOLOSHOWDOWN,
        DUOSHOWDOWN,
        SPIRITWARS,
        HOTZONE,
        KNOCKOUT,
        DUELS,
        BRAWLBALL5V5,
        GEMGRAB5V5,
        KNOCKOUT5V5,
        TRIOSHOWDOWN,
        SOULCOLLOCTOR,
        RANKEDGEMGRAB,
        RANKEDHEIST,
        RANKEDBOUNTY,
        RANKEDBRAWLBALL,
        RANKEDSOLOSHOWDOWN,
        RANKEDDUOSHOWDOWN,
        RANKEDSPIRITWARS,
        RANKEDHOTZONE,
        RANKEDKNOCKOUT,
        RANKEDDUELS,
        RANKEDTRIOSHOWDOWN,
        RANKEDSOULCOLLOCTOR,
    }

    //TODO: inserire le costanti come lez prof
    public static String knockout = "KNOCKOUT";
    public static String StormyPlains = "Stormy Plains";
    //TODO: generare lista brawlers
    public static List<Battle> getBattleList() {
        //TODO: inserire lista
        return null;
    }
    //TODO: generare lista brawlers
    public static List<Brawler> getBrawlerList() {
        //TODO: inserire lista
        return null;
    }
}
