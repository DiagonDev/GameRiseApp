package xyz.brawl.gamerise.model.repository.brawler;

import android.content.Context;

import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;


public class BrawlerRepository extends AbstractRepository implements IBrawlerRepository {
    public BrawlerRepository(Context context, ResponseCallback responseCallback) {
        super(context, responseCallback);
    }


    @Override
    public void fetchBrawlerList() {
        get(apiService.getBrawlerList());
    }

    @Override
    public void fetchBrawler(int brawlerId) {

    }

}

