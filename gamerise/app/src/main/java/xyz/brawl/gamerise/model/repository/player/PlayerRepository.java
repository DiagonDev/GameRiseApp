package xyz.brawl.gamerise.model.repository.player;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Response;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.player.BasePlayerRemoteDataSource;
import xyz.brawl.gamerise.model.data.datasource.player.PlayerRemoteDataSource;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;

public class PlayerRepository implements PlayerCallBack{
    private final MutableLiveData<Result> allPlayerLiveData;
    //private final BasePlayerLocalDataSource playerLocalDataSource;
    private final BasePlayerRemoteDataSource playerRemoteDataSource;

    public PlayerRepository(BasePlayerRemoteDataSource playerRemoteDataSource) {
        allPlayerLiveData = new MutableLiveData<>();
        //this.playerLocalDataSource = playerLocalDataSource;
        this.playerRemoteDataSource = playerRemoteDataSource;
        //this.playerLocalDataSource.setPlayerCallBack(this);
        this.playerRemoteDataSource.setPlayerCallBack(this);
    }

    public MutableLiveData<Result> fetchPlayer(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            playerRemoteDataSource.getPlayerName(tagId);
        }else{
            //metodo che richiama il local
        }
        return allPlayerLiveData;
    }



    @Override
    public void onSuccessFromLocal(String namePlayer) {

    }

    @Override
    public void onFailureFromLocal(Exception exception) {

    }

    @Override
    public void onSuccessFromRemote(String namePlayer, long lastUpdate) {
        Result result = new Result.Success(namePlayer);
        allPlayerLiveData.postValue(result);
    }

    @Override
    public void onFailureFromRemote(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allPlayerLiveData.postValue(resultError);
    }
}