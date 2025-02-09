package xyz.brawl.gamerise.model.repository.brawler;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersRemoteDataSource;

public class BrawlersRepository implements BrawlersCallBack {
    private final MutableLiveData<Result> allBrawlerLiveData;
    private final BaseBrawlersLocalDataSource brawlerLocalDataSource;
    private final BaseBrawlersRemoteDataSource brawlerRemoteDataSource;

    public BrawlersRepository(BaseBrawlersLocalDataSource brawlerLocalDataSource, BaseBrawlersRemoteDataSource brawlerRemoteDataSource) {
        allBrawlerLiveData = new MutableLiveData<>();
        this.brawlerLocalDataSource = brawlerLocalDataSource;
        this.brawlerRemoteDataSource = brawlerRemoteDataSource;
        this.brawlerLocalDataSource.setBrawlerCallBack(this);
        this.brawlerRemoteDataSource.setBrawlerCallBack(this);
    }

    // qua va la logica
    public MutableLiveData<Result> fetchBrawlers(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            brawlerRemoteDataSource.getBrawlerList(tagId);
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

    @Override
    public void onSuccessFromRemote(List<BrawlerEntry> brawler, long lastUpdate) {
        brawlerLocalDataSource.insertBrawlers(brawler);
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBrawlerLiveData.postValue(resultError);
    }
}

