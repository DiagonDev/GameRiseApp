package xyz.brawl.gamerise.util;

import xyz.brawl.gamerise.R;

public class Constants {

    public static final String API_ENDPOINT_URL = "https://sk8.fun:5223/";

    public enum GameMode{
        GEMGRAB(R.drawable.game_mode_gemgrab),
        HEIST(R.drawable.game_mode_heist),
        BOUNTY(R.drawable.game_mode_bounty),
        BRAWLBALL(R.drawable.game_mode_brawlball),
        SOLOSHOWDOWN(R.drawable.game_mode_showdown_solo),
        DUOSHOWDOWN(R.drawable.game_mode_showdown_duo),
        SPIRITWARS(R.drawable.game_mode_spirit_wars),
        HOTZONE(R.drawable.game_mode_hotzone),
        KNOCKOUT(R.drawable.game_mode_knockout),
        DUELS(R.drawable.game_mode_duels),
        BRAWLBALL5V5(R.drawable.game_mode_brawlball),
        GEMGRAB5V5(R.drawable.game_mode_gemgrab),
        KNOCKOUT5V5(R.drawable.game_mode_knockout),
        TRIOSHOWDOWN(R.drawable.game_mode_showdown_trio),
        SOULCOLLOCTOR(R.drawable.game_mode_soul_collector),
        RANKEDGEMGRAB(R.drawable.game_mode_gemgrab),
        RANKEDHEIST(R.drawable.game_mode_heist),
        RANKEDBOUNTY(R.drawable.game_mode_bounty),
        RANKEDBRAWLBALL(R.drawable.game_mode_brawlball),
        RANKEDSOLOSHOWDOWN(R.drawable.game_mode_showdown_solo),
        RANKEDDUOSHOWDOWN(R.drawable.game_mode_showdown_duo),
        RANKEDSPIRITWARS(R.drawable.game_mode_spirit_wars),
        RANKEDHOTZONE(R.drawable.game_mode_hotzone),
        RANKEDKNOCKOUT(R.drawable.game_mode_knockout),
        RANKEDDUELS(R.drawable.game_mode_duels),
        RANKEDTRIOSHOWDOWN(R.drawable.game_mode_showdown_trio),
        RANKEDSOULCOLLOCTOR(R.drawable.game_mode_soul_collector);
        private final int iconModeid;

        GameMode(int iconModeid) {
            this.iconModeid = iconModeid;
        }

        public int getIconModeid() {
            return iconModeid;
        }
    }

    //TODO: inserire le costanti come lez prof
    public static String knockout = "KNOCKOUT";
    public static String StormyPlains = "Stormy Plains";


}
