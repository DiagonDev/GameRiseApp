package xyz.brawl.gamerise.model.data.battle;

import android.graphics.drawable.Drawable;

public class Battle {
    public String title;
    public String subTitle;
    public String trophies;
    public Drawable iconMod;
    public Drawable iconRanked;
    public Drawable iconBackGroundBot;
    public Drawable iconPlayer1;
    public Drawable iconPlayer2;
    public Drawable iconPlayer3;
    public Drawable iconBackgroundTop;

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
        private Drawable iconMod;
        private Drawable iconRanked;
        private Drawable iconBackGroundBot;
        private Drawable iconPlayer1;
        private Drawable iconPlayer2;
        private Drawable iconPlayer3;
        private Drawable iconBackgroundTop;


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

        public BattleBuilder iconMod(Drawable iconMod) {
            this.iconMod = iconMod;
            return this;
        }

        public BattleBuilder iconRanked(Drawable iconRanked) {
            this.iconRanked = iconRanked;
            return this;
        }

        public BattleBuilder iconBackGroundBot(Drawable iconBackGroundBot) {
            this.iconBackGroundBot = iconBackGroundBot;
            return this;
        }

        public BattleBuilder iconPlayer1(Drawable iconPlayer1) {
            this.iconPlayer1 = iconPlayer1;
            return this;
        }

        public BattleBuilder iconPlayer2(Drawable iconPlayer2) {
            this.iconPlayer2 = iconPlayer2;
            return this;
        }

        public BattleBuilder iconPlayer3(Drawable iconPlayer3) {
            this.iconPlayer3 = iconPlayer3;
            return this;
        }

        public BattleBuilder iconBackgroundTop(Drawable iconBackgroundTop) {
            this.iconBackgroundTop = iconBackgroundTop;
            return this;
        }

        public Battle build() {
            return new Battle(this);
        }
    }
}