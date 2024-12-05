package xyz.brawl.gamerise.model.data.brawler;

import android.graphics.drawable.Drawable;

public class StarPower {

    int id;
    String name;
    Drawable icon;

    public StarPower(int id, String name, Drawable icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
}
