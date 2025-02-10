package xyz.brawl.gamerise.model.repository.brawler;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.brawler.BaseBrawlersRemoteDataSource;
import xyz.brawl.gamerise.model.data.singleton.GameAccountSingleton;

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
    public MutableLiveData<Result> fetchBrawlers(String tagId, boolean connected) {
        if (connected && !GameAccountSingleton.getInstance().isChecked()){
            brawlerRemoteDataSource.getBrawlerList(tagId);
        } else {
            brawlerLocalDataSource.getBrawlers(tagId);
        }
        return allBrawlerLiveData;
    }

    public MutableLiveData<Result> insertBrawlers(List<BrawlerEntry> brawlersToInsert){
        brawlerLocalDataSource.insertBrawlers(brawlersToInsert);
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
        if(GameAccountSingleton.getInstance().isChecked())
            brawlerLocalDataSource.insertBrawlers(brawler);
        else{
            Result result = new Result.Success(brawler);
            allBrawlerLiveData.postValue(result);
        }
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBrawlerLiveData.postValue(resultError);
    }
}

