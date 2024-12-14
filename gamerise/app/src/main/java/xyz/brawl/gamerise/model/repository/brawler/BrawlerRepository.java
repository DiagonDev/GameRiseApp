package xyz.brawl.gamerise.model.repository.brawler;

import android.content.Context;
import android.util.Log;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.ItemsResponse;
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
        get(apiService.getBrawler(brawlerId));
    }

    @Override
    protected void handleApiResponse(Response response) {
        //leo - handleApiResponse viene chiamato in mezzo a `get()`, vedere AbstractRepository.java

        //leo - questo metodo viene chiamato sia con `fetchBrawlerList` che con `fetchBrawler`
        // dobbiamo considerare entrambi i casi
        // vale comunque la pena di fare così perché altrimenti abbiamo due metodi 'fetch..()` uguali
        if (response.body() instanceof ItemsResponse) {
            ItemsResponse ir = (ItemsResponse) response.body();
            responseCallback.onSuccess(ir.getItems() ,response.raw().receivedResponseAtMillis());

            for (BrawlerEntry b : ir.getItems())
                Log.d("Query brawler: ", b.getName());
        }
        else if (response.body() instanceof BrawlerEntry) {
            BrawlerEntry be = (BrawlerEntry) response.body();
            responseCallback.onSuccess(be ,response.raw().receivedResponseAtMillis());

            Log.d("Query brawler singola: ", be.getName());
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }

}

