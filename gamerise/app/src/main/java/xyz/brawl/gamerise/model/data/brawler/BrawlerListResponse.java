package xyz.brawl.gamerise.model.data.brawler;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// Response class containing only the 'items' array
//leo - il json ritornato da /brawlers contiene un mega-array di BrawlerEntry, contenuti in un oggetto "items"
//      questo oggetto intermedio ci permette di usare GsonConverterFactory
public class BrawlerListResponse {
    @SerializedName("items")
    private List<BrawlerEntry> items;

    public List<BrawlerEntry> getItems() {
        return items;
    }
}