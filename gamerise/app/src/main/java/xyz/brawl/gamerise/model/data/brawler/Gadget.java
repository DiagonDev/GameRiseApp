package xyz.brawl.gamerise.model.data.brawler;

import android.graphics.drawable.Drawable;

public class Gadget {
    public int id;
    public String name;
    Drawable icon;

    public Gadget(int id, String name, Drawable icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Gadget(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
