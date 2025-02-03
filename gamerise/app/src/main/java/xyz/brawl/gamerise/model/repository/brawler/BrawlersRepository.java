package xyz.brawl.gamerise.model.repository.brawler;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersLocalDataSource;

public class BrawlersRepository implements BrawlersCallBack {
    private final MutableLiveData<Result> allBrawlerLiveData;
    private final BaseBrawlersLocalDataSource brawlerLocalDataSource;
    public BrawlersRepository(BaseBrawlersLocalDataSource brawlerLocalDataSource) {
        allBrawlerLiveData = new MutableLiveData<>();
        this.brawlerLocalDataSource = brawlerLocalDataSource;
        this.brawlerLocalDataSource.setBrawlerCallBack(this);
    }

    public MutableLiveData<Result> fetchBrawlers(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //articleRemoteDataSource.getArticles(country);
            //get(apiService.getBattlelog(tagId));
        } else {
            brawlerLocalDataSource.getBrawlers(tagId);
        }
        return allBrawlerLiveData;
    }

    @Override
    public void onSuccessFromLocal(List<BrawlerEntry> brawler) {
        Result result = new Result.Success(brawler);
        allBrawlerLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBrawlerLiveData.postValue(resultError);
    }
/*
    @Override
    public void fetchBrawlerList() {
        get(apiService.getBrawlerList());
    }

    @Override
    public void fetchBrawler(int brawlerId) {
        get(apiService.getBrawler(brawlerId));
    }

    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        //leo - handleApiResponse viene chiamato in mezzo a `get()`, vedere AbstractRepository.java

        //leo - questo metodo viene chiamato sia con `fetchBrawlerList` che con `fetchBrawler`
        // dobbiamo considerare entrambi i casi
        // vale comunque la pena di fare così perché altrimenti abbiamo due metodi 'fetch..()` uguali
        if (response.body() instanceof BrawlerListResponse) {
            BrawlerListResponse ir = (BrawlerListResponse) response.body();
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

    }*/

}

