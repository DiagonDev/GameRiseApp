package xyz.brawl.gamerise.model.data.battle;

import android.graphics.drawable.Drawable;

import xyz.brawl.gamerise.R;

public class Battle {
    public String title;
    public String subTitle;
    public String trophies;
    public Drawable iconMod, iconRanked, iconBackGroundBot;
    public Drawable iconPlayer1, iconPlayer2, iconPlayer3;
    public Drawable iconBackgroundTop;

    public Battle(String title, Drawable iconPlayer3, Drawable iconPlayer2, Drawable iconPlayer1,
                  Drawable iconBackGroundBot, Drawable iconRanked, Drawable iconMod, String trophies, String subTitle, Drawable iconBackgroundTop) {
        this.title = title;
        this.iconPlayer3 = iconPlayer3;
        this.iconPlayer2 = iconPlayer2;
        this.iconPlayer1 = iconPlayer1;
        this.iconBackGroundBot = iconBackGroundBot;
        this.iconRanked = iconRanked;
        this.iconMod = iconMod;
        this.trophies = trophies;
        this.subTitle = subTitle;
        this.iconBackgroundTop = iconBackgroundTop;
    }
    //provvisorio
    public Battle() {
        this.title = "title";
    }
}
