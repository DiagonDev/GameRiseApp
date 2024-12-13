package xyz.brawl.gamerise.util;

import java.util.List;

import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogEntry;
import xyz.brawl.gamerise.model.data.battle.api.PlayerEntry;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;

public class Constants {

    public static final String API_ENDPOINT_URL = "https://sk8.fun:5223/";

    public enum GameMode {
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

    public enum BrawlerPin {
        AMBER(R.drawable.amber_pin),
        SHELLY(R.drawable.shelly_pin),
        COLT(R.drawable.colt_pin),
        BULL(R.drawable.bull_pin),
        BROCK(R.drawable.brock_pin),
        RICO(R.drawable.rico_pin),
        BARLEY(R.drawable.barley_pin),
        JESSIE(R.drawable.jessie_pin),
        NITA(R.drawable.nita_pin),
        DYNAMIKE(R.drawable.dynamike_pin),
        EL_PRIMO(R.drawable.el_primo_pin),
        MORTIS(R.drawable.mortis_pin),
        CROW(R.drawable.crow_pin),
        POCO(R.drawable.poco_pin),
        BO(R.drawable.bo_pin),
        PIPER(R.drawable.piper_pin),
        PAM(R.drawable.pam_pin),
        TARA(R.drawable.tara_pin),
        DARRYL(R.drawable.darryl_pin),
        PENNY(R.drawable.penny_pin),
        FRANK(R.drawable.frank_pin),
        GENE(R.drawable.gene_pin),
        TICK(R.drawable.tick_pin),
        LEON(R.drawable.leon_pin),
        ROSA(R.drawable.rosa_pin),
        CARL(R.drawable.carl_pin),
        BIBI(R.drawable.bibi_pin),
        BIT_8(R.drawable.bit_8_pin),
        SANDY(R.drawable.sandy_pin),
        BEA(R.drawable.bea_pin),
        EMZ(R.drawable.emz_pin),
        MAX(R.drawable.max_pin),
        JACKY(R.drawable.jacky_pin),
        NANI(R.drawable.nani_pin),
        SPROUT(R.drawable.sprout_pin),
        SURGE(R.drawable.surge_pin),
        COLETTE(R.drawable.colette_pin),
        BYRON(R.drawable.byron_pin),
        EDGAR(R.drawable.edgar_pin),
        STU(R.drawable.stu_pin),
        SQUEAK(R.drawable.squeak_pin),
        GROM(R.drawable.grom_pin),
        BUZZ(R.drawable.buzz_pin),
        GRIFF(R.drawable.griff_pin),
        ASH(R.drawable.ash_pin),
        LOLA(R.drawable.lola_pin),
        GUS(R.drawable.gus_pin),
        CHESTER(R.drawable.chester_pin),
        RT(R.drawable.rt_pin),
        MAISIE(R.drawable.maisie_pin),
        CORDELIUS(R.drawable.cordelius_pin),
        PEARL(R.drawable.pearl_pin),
        DRACO(R.drawable.draco_pin),
        OLLIE(R.drawable.ollie_pin),
        MEEPLE(R.drawable.meeple_pin),
        BUZZ_LIGHTYEAR(iconStandard), //ignoreremo
        JUJU(R.drawable.juju_pin),
        SHADE(R.drawable.shade_pin),
        KENJI(R.drawable.kenji_pin),
        MOE(R.drawable.moe_pin),
        CLANCY(R.drawable.clancy_pin),
        BERRY(R.drawable.berry_pin),
        LILY(R.drawable.lily_pin),
        ANGELO(R.drawable.angelo_pin),
        MELODIE(R.drawable.melodie_pin),
        LARRY_AND_LAWRIE(R.drawable.larry_and_lawrie_pin),
        KIT(R.drawable.kit_pin),
        MICO(R.drawable.mico_pin),
        CHARLIE(R.drawable.charlie_pin),
        CHUCK(R.drawable.chuck_pin),
        DOUG(R.drawable.doug_pin),
        HANK(R.drawable.hank_pin),
        WILLOW(R.drawable.willow_pin),
        MANDY(R.drawable.mandy_pin),
        GRAY(R.drawable.gray_pin),
        BUSTER(R.drawable.buster_pin),
        SAM(R.drawable.sam_pin),
        OTIS(R.drawable.otis_pin),
        BONNIE(R.drawable.bonnie_pin),
        JANET(R.drawable.janet_pin),
        EVE(R.drawable.eve_pin),
        FANG(R.drawable.fang_pin),
        SPIKE(R.drawable.spike_pin),
        GALE(R.drawable.gale_pin),
        MR_P(R.drawable.mrp_pin),
        LOU(R.drawable.lou_pin);

        private final int iconPlayerId;

        BrawlerPin(int iconPlayerId) {
            this.iconPlayerId = iconPlayerId;
        }

        public int getIconPlayerId() {
            return iconPlayerId;
        }
    }
    //Questo metodo ritorna la lista di players del team del giocatore (tag)
    /*public static List<PlayerEntry> getTeamMembers(BattleLogEntry battleLogEntry) {
        List<TeamEntry> teams = battleLogEntry.getBattle().getTeams();
        for (TeamEntry team : teams) {
            for (PlayerEntry playerEntry : team.getPlayers()) {
                if(playerEntry.getTag().equals(GameAccountSingleton.getInstance().getUserTag())){
                    return team.getPlayers();
                }
            }
        }
        return null;
    }

   //Dichiara se la lista di players è vuota o meno
    public static String[] getPlayersBrawler(BattleLogEntry battleLogEntry){
        List<PlayerEntry> playerEntryList = getTeamMembers(battleLogEntry);
        String[] playersBrawler = {null, null, null};
        for (int i = 0; i < playerEntryList.size(); i++) {
            playersBrawler[i] = playerEntryList.get(i).getBrawler().getName();
        }
        return playersBrawler;
    }*/

    public static int iconRanked = R.drawable.ranked_icon;
    public static int iconStandard = R.drawable.standard_icon;

    public static String tagLeo = "#VQCV92Q9";
    public static String tagTeo = "#989VGUU0";
    //TODO: inserire le costanti come lez prof
    public static String knockout = "KNOCKOUT";
    public static String StormyPlains = "Stormy Plains";


}
