package xyz.brawl.gamerise.model.data.datasource.brawler;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.BrawlerV2;

// Response class containing only the 'items' array
public class ItemsResponse {
    @SerializedName("items")
    private List<BrawlerV2> items;

    public List<BrawlerV2> getItems() {
        return items;
    }
}