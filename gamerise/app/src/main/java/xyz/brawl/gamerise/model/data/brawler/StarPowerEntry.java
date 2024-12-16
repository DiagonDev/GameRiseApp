package xyz.brawl.gamerise.model.data.brawler;

import com.google.gson.annotations.SerializedName;

public class StarPowerEntry {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
