package xyz.brawl.gamerise.model.data.battle;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import xyz.brawl.gamerise.model.data.tag.Tag;

@Entity
public class Battle {

    //autoGenerate true perchè non voglio doverlo specificare
    @PrimaryKey(autoGenerate = true)
    public int battleId;
    /*TODO:  mi serve la foreignKey di Tag.class
    @ForeignKey(Tag.class)
    public String tag;*/

    public String title;
    public String subTitle;
    public String trophies;
    public int iconMod;
    public int iconRanked;
    public int iconBackGroundBot;
    public int iconPlayer1;
    public int iconPlayer2;
    public int iconPlayer3;
    public int iconBackgroundTop;

    //Costruttore vuoto per Room
    public Battle(){}

    public Battle(BattleBuilder battleBuilder) {
        this.title = battleBuilder.title;
        this.subTitle = battleBuilder.subTitle;
        this.trophies = battleBuilder.trophies;
        this.iconMod = battleBuilder.iconMod;
        this.iconRanked = battleBuilder.iconRanked;
        this.iconBackGroundBot = battleBuilder.iconBackGroundBot;
        this.iconPlayer1 = battleBuilder.iconPlayer1;
        this.iconPlayer2 = battleBuilder.iconPlayer2;
        this.iconPlayer3 = battleBuilder.iconPlayer3;
        this.iconBackgroundTop = battleBuilder.iconBackgroundTop;
    }

    /**
     * Utilizzo di Builder Pattern
     */
    public static class BattleBuilder {
        private String title;
        private String subTitle;
        private String trophies;
        private int iconMod;
        private int iconRanked;
        private int iconBackGroundBot;
        private int iconPlayer1;
        private int iconPlayer2;
        private int iconPlayer3;
        private int iconBackgroundTop;


        public BattleBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BattleBuilder subTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;
        }

        public BattleBuilder trophies(String trophies) {
            this.trophies = trophies;
            return this;
        }

        public BattleBuilder iconMode(int iconMod) {
            this.iconMod = iconMod;
            return this;
        }

        public BattleBuilder iconRanked(int iconRanked) {
            this.iconRanked = iconRanked;
            return this;
        }

        public BattleBuilder iconBackGroundBot(int iconBackGroundBot) {
            this.iconBackGroundBot = iconBackGroundBot;
            return this;
        }

        public BattleBuilder iconPlayer1(int iconPlayer1) {
            this.iconPlayer1 = iconPlayer1;
            return this;
        }

        public BattleBuilder iconPlayer2(int iconPlayer2) {
            this.iconPlayer2 = iconPlayer2;
            return this;
        }

        public BattleBuilder iconPlayer3(int iconPlayer3) {
            this.iconPlayer3 = iconPlayer3;
            return this;
        }

        public BattleBuilder iconBackgroundTop(int iconBackgroundTop) {
            this.iconBackgroundTop = iconBackgroundTop;
            return this;
        }

        public Battle build() {
            return new Battle(this);
        }
    }
}