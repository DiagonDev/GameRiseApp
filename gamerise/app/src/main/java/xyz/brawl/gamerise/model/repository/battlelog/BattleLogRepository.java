package xyz.brawl.gamerise.model.repository.battlelog;

import static xyz.brawl.gamerise.model.data.battle.BattleMapper.mapToBattleLogEntries;
import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;
import java.util.List;
import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleLocalDataSource;


public class BattleLogRepository implements BattleLogCallback {

    //private static final String TAG = BattleLogRepository.class.getSimpleName();
    private final MutableLiveData<Result> allBattleLogLiveData;
    private final BaseBattleLocalDataSource battleLocalDataSource;
    //TODO: aggiongere il REMOTE
    public BattleLogRepository(BaseBattleLocalDataSource battleLocalDataSource) {
        allBattleLogLiveData = new MutableLiveData<>();
        this.battleLocalDataSource = battleLocalDataSource;
        this.battleLocalDataSource.setBattleLogCallback(this);
    }

    /**
     * playerTag tag del giocatore, per ora impostato su teo
     * @return null provvisorio
     */

    public MutableLiveData<Result> fetchBattleLog(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //articleRemoteDataSource.getArticles(country);
            //get(apiService.getBattlelog(tagId));
        } else {
            battleLocalDataSource.getBattles(tagId);
        }
        return allBattleLogLiveData;
    }


    /// Questo metodo serve per permettere ai repository di gestire le risposte API in maniera differente
    /*@Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof BattleLogApiResponse) {
            BattleLogApiResponse blar = (BattleLogApiResponse) response.body();
            responseCallback.onSuccess(blar.getBattleResponseList(), response.raw().receivedResponseAtMillis());
            *//* TODO: implementare la cosa
            List<Battle> battles = BattleMapper.mapToBattles(blar.getBattleResponseList());
            responseCallback.onSuccess(battles, response.raw().receivedResponseAtMillis());*//*
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }*/

    @Override
    public void onSuccessFromRemote(List<Battle> battles, long lastUpdate) {

    }

    @Override
    public void onFailureFromRemote(String errorMessage) {

    }

    //TODO: da completare
    @Override
    public void onSuccessFromLocal(List<Battle> battles) {
        Result result = new Result.Success(new BattleLogApiResponse(mapToBattleLogEntries(battles)));
        allBattleLogLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allBattleLogLiveData.postValue(resultError);
    }

    /*public void onSuccessFromLocal(List<Article> articleList) {
        Result.ArticleSuccess result = new Result.ArticleSuccess(new ArticleAPIResponse(articleList));
        allArticlesMutableLiveData.postValue(result);
    }

    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allArticlesMutableLiveData.postValue(resultError);
        favoriteNewsMutableLiveData.postValue(resultError);
    }*/
}

