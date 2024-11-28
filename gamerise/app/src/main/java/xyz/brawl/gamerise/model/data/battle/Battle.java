package xyz.brawl.gamerise.model.data.battle;

import android.graphics.drawable.Drawable;

import xyz.brawl.gamerise.R;

public class Battle {
    public String title;
    public String subTitle;
    public String trophies;
    public Drawable iconMod, iconRanked, iconBackGround;
    public Drawable iconPlayer1, iconPlayer2, iconPlayer3;

    public Battle(String title, Drawable iconPlayer3, Drawable iconPlayer2, Drawable iconPlayer1,
                  Drawable iconBackGround, Drawable iconRanked, Drawable iconMod, String trophies, String subTitle) {
        this.title = title;
        this.iconPlayer3 = iconPlayer3;
        this.iconPlayer2 = iconPlayer2;
        this.iconPlayer1 = iconPlayer1;
        this.iconBackGround = iconBackGround;
        this.iconRanked = iconRanked;
        this.iconMod = iconMod;
        this.trophies = trophies;
        this.subTitle = subTitle;
    }
    //provvisorio
    public Battle() {
        this.title = "title";
    }
}
