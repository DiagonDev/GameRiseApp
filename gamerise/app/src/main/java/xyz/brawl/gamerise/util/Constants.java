package xyz.brawl.gamerise.util;

import java.util.ArrayList;
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

        public static GameMode fromModeString(String mode) {
            try {
                // Convert camelCase a SNAKE_CASE
                String enumKey = mode.toUpperCase().replaceAll("([a-z])([A-Z])", "$1_$2");
                return GameMode.valueOf(enumKey);
            } catch (IllegalArgumentException e) {
                // Restituisci un valore predefinito in caso di errore
                return GameMode.GEMGRAB; // Sostituisci con un valore di fallback appropriato
            }
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
        R_TRATTINO_T(R.drawable.rt_pin),
        MAISIE(R.drawable.maisie_pin),
        CORDELIUS(R.drawable.cordelius_pin),
        PEARL(R.drawable.pearl_pin),
        DRACO(R.drawable.draco_pin),
        OLLIE(R.drawable.ollie_pin),
        MEEPLE(R.drawable.meeple_pin),
        BUZZ_LIGHTYEAR(R.drawable.sandy_pin), //ignoreremo
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
        public static BrawlerPin fromBrawlerPinString(String mode) {
            try {
                // Sostituisci i caratteri non validi
                String enumKey = mode.toUpperCase()
                        .replace(" & ", "_AND_")
                        .replace(" ", "_")
                        .replace("-", "_TRATTINO_");
                return BrawlerPin.valueOf(enumKey);
            } catch (IllegalArgumentException e) {
                // Fallback in caso di errore
                return BrawlerPin.LOU; // Valore predefinito
            }
        }

        public static BrawlerPin fromIconPlayerId(int iconId) {
            for (BrawlerPin brawlerPin : BrawlerPin.values()) {
                if (brawlerPin.getIconPlayerId() == iconId) {
                    return brawlerPin;
                }
            }
            return BrawlerPin.LOU; // Valore predefinito in caso di errore
        }

        public String getBrawlerPinString() {
            return this.name().toLowerCase().replace("_", " ");
        }

    }
    //Questo metodo ritorna la lista di players del team del giocatore (tag)
    public static List<PlayerEntry> getTeamMembers(List<List<PlayerEntry>> teams) {
        for (List<PlayerEntry> playerEntryList : teams) {
            for (PlayerEntry playerEntry : playerEntryList) {
                if(playerEntry.getTag().equals("#" + GameAccountSingleton.getInstance().getUserTag())){
                    return playerEntryList;
                }
            }
        }
        return null;
    }

   //Dichiara se la lista di players è vuota o meno
    public static String[] getPlayersBrawler(BattleLogEntry battleLogEntry){
        List<List<PlayerEntry>> teams = battleLogEntry.getBattle().getTeams();
        List<PlayerEntry> playerEntryList = new ArrayList<>();
        String[] playersBrawler = {null, null, null};
        if(teams == null) {
            playerEntryList = battleLogEntry.getBattle().getPlayers();
            for (PlayerEntry playerEntry: playerEntryList) {
                if(playerEntry.getTag().equals("#" + GameAccountSingleton.getInstance().getUserTag())){
                    playersBrawler[0] = playerEntry.getBrawler().getName();
                    return playersBrawler;
                }
            }
        }else playerEntryList = getTeamMembers(teams);

        for (int i = 0; i < playerEntryList.size(); i++) {
            playersBrawler[i] = playerEntryList.get(i).getBrawler().getName();
        }
        return playersBrawler;

    }

    public enum GadgetPin {
        FIRE_STARTERS(R.drawable.amber_gadget_01),
        DANCING_FLAMES(R.drawable.amber_gadget_02),
        FAST_FORWARD(R.drawable.shelly_gadget_01),
        CLAY_PIGEONS(R.drawable.shelly_gadget_02),
        SPEEDLOADER(R.drawable.colt_gadget_01),
        SILVER_BULLET(R.drawable.colt_gadget_02),
        T_TRATTINO_BONE_INJECTOR(R.drawable.bull_gadget_01),
        STOMPER(R.drawable.bull_gadget_02),
        ROCKET_LACES(R.drawable.brock_gadget_01),
        ROCKET_FUEL(R.drawable.brock_gadget_02),
        MULTIBALL_LAUNCHER(R.drawable.rico_gadget_01),
        BOUNCY_CASTLE(R.drawable.rico_gadget_02),
        STICKY_SYRUP_MIXER(R.drawable.barley_gadget_01),
        HERBAL_TONIC(R.drawable.barley_gadget_02),
        SPARCK_PLUG(R.drawable.jessie_gadget_01),
        RECOIL_SPRING(R.drawable.jessie_gadget_02),
        BEAR_PAWS(R.drawable.nita_gadget_01),
        FAUX_FUR(R.drawable.nita_gadget_02),
        FIDGET_SPINNER(R.drawable.dynamike_gadget_01),
        SATCHEL_CHARGER(R.drawable.dynamike_gadget_02),
        SUPLEX_SUPPLEMENT(R.drawable.elprimo_gadget_01),
        ASTEROID_BELT(R.drawable.elprimo_gadget_02),
        COMBO_SPINNER(R.drawable.mortis_gadget_01),
        SURVIVAL_SHOVEL(R.drawable.mortis_gadget_02),
        DEFENSE_BOOSTERS(R.drawable.crow_gadget_01),
        SLOWING_TOXIN(R.drawable.crow_gadget_02),
        TUNING_FORK(R.drawable.poco_gadget_01),
        PROTECTIVE_TUNES(R.drawable.poco_gadget_02),
        SUPER_TOTEM(R.drawable.bo_gadget_01),
        TRIPWIRE(R.drawable.bo_gadget_02),
        AUTO_AIMER(R.drawable.piper_gadget_01),
        HOMEMADE_RECIPE(R.drawable.piper_gadget_02),
        PULSE_MODULATOR(R.drawable.pam_gadget_01),
        SCRAPSUCKER(R.drawable.pam_gadget_02),
        PSYCHIC_ENHANCER(R.drawable.tara_gadget_01),
        SUPPORT_FROM_BEYOND(R.drawable.tara_gadget_02),
        RECOILING_ROTATOR(R.drawable.darryl_gadget_01),
        TAR_BARREL(R.drawable.darryl_gadget_02),
        SALTY_BARREL(R.drawable.penny_gadget_01),
        TRUSTY_SPYGLASS(R.drawable.penny_gadget_02),
        ACTIVE_NOISE_CANCELLING(R.drawable.frank_gadget_01),
        IRRESISTIBLE_ATTRACTION(R.drawable.frank_gadget_02),
        LAMP_BLOWOUT(R.drawable.gene_gadget_01),
        VENGEFUL_SPIRITS(R.drawable.gene_gadget_02),
        MINE_MANIA(R.drawable.tick_gadget_01),
        LAST_HURRAH(R.drawable.tick_gadget_02),
        CLONE_PROJECTOR(R.drawable.leon_gadget_01),
        LOLLIPOP_DROP(R.drawable.leon_gadget_02),
        GROW_LIGHT(R.drawable.rosa_gadget_01),
        UNFRIENDLY_BUSHES(R.drawable.rosa_gadget_02),
        HEAT_EJECTOR(R.drawable.carl_gadget_01),
        FLYING_HOOK(R.drawable.carl_gadget_02),
        VITAMIN_BOOSTER(R.drawable.bibi_gadget_01),
        EXTRA_STICKY(R.drawable.bibi_gadget_02),
        CHEAT_CARTRIDGE(R.drawable._bit_gadget_01),
        EXTRA_CREDITS(R.drawable._bit_gadget_02),
        SLEEP_STIMULATOR(R.drawable.sandy_gadget_01),
        SWEET_DREAMS(R.drawable.sandy_gadget_02),
        HONEY_MOLASSES(R.drawable.bea_gadget_01),
        RATTLED_HIVE(R.drawable.bea_gadget_02),
        FRIENDZONER(R.drawable.emz_gadget_01),
        ACID_SPRAY(R.drawable.emz_gadget_02),
        PHASE_SHIFTER(R.drawable.max_gadget_01),
        SNEAKY_SNEAKERS(R.drawable.max_gadget_02),
        PNEUMATIC_BOOSTER(R.drawable.jacky_gadget_01),
        REBUILD(R.drawable.jacky_gadget_02),
        WARPIN_ASTERISCO__TIME(R.drawable.nani_gadget_01),
        RETURN_TO_SENDER(R.drawable.nani_gadget_02),
        GARDEN_MULCHER(R.drawable.sprout_gadget_01),
        TRANSPLANT(R.drawable.sprout_gadget_02),
        POWER_SURGE(R.drawable.surge_gadget_01),
        POWER_SHIELD(R.drawable.surge_gadget_02),
        NA_TRATTINO_AH_ESCLAMATIVO(R.drawable.colette_gadget_01),
        GOTCHA_ESCLAMATIVO(R.drawable.colette_gadget_02),
        SHOT_IN_THE_ARM(R.drawable.byron_gadget_01),
        BOOSTER_SHOTS(R.drawable.byron_gadget_02),
        LET_ASTERISCO_S_FLY(R.drawable.edgar_gadget_01),
        HARDCORE(R.drawable.edgar_gadget_02),
        SPEED_ZONE(R.drawable.stu_gadget_01),
        BREAKTHROUGH(R.drawable.stu_gadget_02),
        WINDUP(R.drawable.squeak_gadget_01),
        RESIDUE(R.drawable.squeak_gadget_02),
        WATCHTOWER(R.drawable.grom_gadget_01),
        RADIO_CHECK(R.drawable.grom_gadget_02),
        RESERVE_BUOY(R.drawable.buzz_gadget_01),
        X_TRATTINO_RAY_TRATTINO_SHADES(R.drawable.buzz_gadget_02),
        PIGGY_BANK(R.drawable.griff_gadget_01),
        COIN_SHOWER(R.drawable.griff_gadget_02),
        CHILL_PILL(R.drawable.ash_gadget_01),
        ROTTEN_BANANA(R.drawable.ash_gadget_02),
        FREEZE_FRAME(R.drawable.lola_gadget_01),
        STUNT_DOUBLE(R.drawable.lola_gadget_02),
        KOOKY_POPPER(R.drawable.gus_gadget_01),
        SOUL_SWITCHER(R.drawable.gus_gadget_02),
        SPICY_DICE(R.drawable.chester_gadget_01),
        CANDY_BEANS(R.drawable.chester_gadget_02),
        OUT_OF_LINE(R.drawable.rt_gadget_01),
        HACKSAW_ESCLAMATIVO(R.drawable.rt_gadget_02),
        DISENGAGE_ESCLAMATIVO(R.drawable.maisie_gadget_01),
        FINISH_THEM_ESCLAMATIVO(R.drawable.maisie_gadget_02),
        REPLANTING(R.drawable.cordelius_gadget_01),
        POISON_MUSHROOM(R.drawable.cordelius_gadget_02),
        OVERCOOKED(R.drawable.pearl_gadget_01),
        MADE_WITH_LOVE(R.drawable.pearl_gadget_02),
        UPPER_CUT(R.drawable.draco_gadget_01),
        LAST_STAND(R.drawable.draco_gadget_02),
        REGULATEUR(R.drawable.gadget_base),
        ALL_EYEZ_ON_ME(R.drawable.gadget_base),
        MANSIONS_OF_MEEPLE(R.drawable.meeple_gadget_01),
        RAGEQUIT(R.drawable.gadget_base),
        VODOO_CHILERO(R.drawable.juju_gadget_01),
        ELEMENTALIST(R.drawable.gadget_base),
        LONGARMS(R.drawable.shade_gadget_01),
        JUMP_SCARE(R.drawable.shade_gadget_02),
        DASHI_DASH(R.drawable.kenji_gadget_01),
        HOSOMAKI_HEALING(R.drawable.kenji_gadget_02),
        DODGY_DIGGING(R.drawable.moe_gadget_01),
        RAT_RACE(R.drawable.moe_gadget_02),
        SNAPPY_SHOOTING(R.drawable.clancy_gadget_01),
        TACTICAL_RETREAT(R.drawable.clancy_gadget_02),
        FRIENDSHIP_IS_GREAT(R.drawable.gadget_base),
        HEALTHY_ADDITIVES(R.drawable.berry_gadget_02),
        VANISH(R.drawable.lily_gadget_01),
        REPOT(R.drawable.lily_gadget_02),
        STINGING_FLIGHT(R.drawable.angelo_gadget_01),
        MASTER_FLETCHER(R.drawable.angelo_gadget_02),
        PERFECT_PITCH(R.drawable.melodie_gadget_01),
        INTERLUDER(R.drawable.melodie_gadget_02),
        ORDER_POINT_SWAP(R.drawable.larry_and_lawrie_gadget_01),
        ORDER_POINT_FALL_BACK(R.drawable.larry_and_lawrie_gadget_02),
        CARDBOARD_BOX(R.drawable.kit_gadget_01),
        CHEESBURGER(R.drawable.kit_gadget_02),
        CLIPPING_SCREAM(R.drawable.mico_gadget_01),
        PRESTO(R.drawable.mico_gadget_02),
        SPIDERS(R.drawable.charlie_gadget_01),
        PERSONAL_SPACE(R.drawable.charlie_gadget_02),
        REROUTING(R.drawable.chuck_gadget_01),
        GHOST_TRAINnER(R.drawable.chuck_gadget_02),
        DOUBLE_SAUSAGE(R.drawable.doug_gadget_01),
        EXTRA_MUSTARD(R.drawable.doug_gadget_02),
        WATER_BALLOONS(R.drawable.hank_gadget_01),
        BARRICADE(R.drawable.hank_gadget_02),
        SPELLBOUND(R.drawable.willow_gadget_01),
        DIVE_IN(R.drawable.willow_gadget_02),
        CARAMELIZE(R.drawable.mandy_gadget_01),
        COOKIE_CRUMBS(R.drawable.mandy_gadget_02),
        WALKING_CANE(R.drawable.gray_gadget_01),
        GRAND_PIANO(R.drawable.gray_gadget_02),
        UTILITY_BELT(R.drawable.buster_gadget_01),
        SLO_MO_REPLAYS(R.drawable.buster_gadget_02),
        MAGNETIC_FIELD(R.drawable.sam_gadget_01),
        PULSE_REPELLENT(R.drawable.sam_gadget_02),
        DORMANT_STAR(R.drawable.otis_gadget_01),
        PHAT_SPLATTER(R.drawable.otis_gadget_02),
        SUGAR_RUSH(R.drawable.bonnie_gadget_01),
        CRASH_TEST(R.drawable.bonnie_gadget_02),
        DROP_THE_BASS(R.drawable.janet_gadget_01),
        BACKSTAGE_PASS(R.drawable.janet_gadget_02),
        GOTTA_GO(R.drawable.eve_gadget_01),
        MOTHERLY_LOVES(R.drawable.eve_gadget_02),
        CORN_FU(R.drawable.fang_gadget_01),
        ROUNDHOUSE_KICK(R.drawable.fang_gadget_02),
        POPPING_PINCUSHION(R.drawable.spike_gadget_01),
        LIFE_PLANT(R.drawable.spike_gadget_02),
        SPRING_EJECTOR(R.drawable.gale_gadget_01),
        TWISTER(R.drawable.gale_gadget_02),
        SERVICE_BELL(R.drawable.mrp_gadget_01),
        PORTER_REINFORCEMENTS(R.drawable.mrp_gadget_02),
        ICE_BLOCK(R.drawable.lou_gadget_01),
        CRYO_SYRUP(R.drawable.lou_gadget_02);

        private final int iconGadgetId;

        GadgetPin(int iconGadgetId) {
            this.iconGadgetId = iconGadgetId;
        }

        public int getIconGadgetId() {
            return iconGadgetId;
        }
        public static GadgetPin fromGadgetPinString(String mode) {
            try {
                // Sostituisci i caratteri non validi
                String enumKey = mode.toUpperCase()
                        .replace(": ", "_POINT_")
                        .replace(" ", "_")
                        .replace("-", "_TRATTINO_")
                        .replace("!", "_ESCLAMATIVO")
                        .replace("'", "_ASTERISCO_")
                        .replace(" ", "_");
                return GadgetPin.valueOf(enumKey);
            } catch (IllegalArgumentException e) {
                // Fallback in caso di errore
                return GadgetPin.FRIENDSHIP_IS_GREAT; // Valore predefinito
            }
        }
    }
    

    public static int iconRanked = R.drawable.ranked_icon;
    public static int iconStandard = R.drawable.standard_icon;

    public static String tagLeo = "#VQCV92Q9";
    public static String tagTeo = "#989VGUU0";
    public static String knockout = "KNOCKOUT";
    public static String StormyPlains = "Stormy Plains";

    public static final int DATABASE_VERSION = 12;
    public static final int FRESH_TIMEOUT = 1000 * 60;
}
