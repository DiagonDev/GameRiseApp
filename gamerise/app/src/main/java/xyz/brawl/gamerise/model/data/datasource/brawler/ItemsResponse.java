package xyz.brawl.gamerise.model.data.datasource.brawler;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;

// Response class containing only the 'items' array
public class ItemsResponse {
    @SerializedName("items")
    private List<BrawlerEntry> items;

    public List<BrawlerEntry> getItems() {
        return items;
    }
}