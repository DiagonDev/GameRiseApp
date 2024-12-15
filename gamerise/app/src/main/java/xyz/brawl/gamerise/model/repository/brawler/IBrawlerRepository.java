package xyz.brawl.gamerise.model.repository.brawler;

public interface IBrawlerRepository {
    void fetchBrawler(int brawlerId);

    void fetchBrawlerList();
}
