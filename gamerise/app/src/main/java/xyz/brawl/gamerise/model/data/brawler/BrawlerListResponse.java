package xyz.brawl.gamerise.model.data.brawler;

import java.util.List;

public class BrawlerListResponse {
    public List<Brawler> items;
    public Paging paging;

    // Inner class for paging if needed
    public static class Paging {
        public Cursors cursors;

        public static class Cursors {
            // Add fields if there are any cursor details
        }
    }

    // Getters and setters (if needed)
}
