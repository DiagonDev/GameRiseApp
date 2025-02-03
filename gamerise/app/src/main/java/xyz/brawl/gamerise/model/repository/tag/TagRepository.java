package xyz.brawl.gamerise.model.repository.tag;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.tag.BaseTagLocalDataSource;
import xyz.brawl.gamerise.model.data.tag.Tag;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;


public class TagRepository implements TagCallback {

    private final MutableLiveData<Result> allTagLiveData;
    private final BaseTagLocalDataSource tagLocalDataSource;

    public TagRepository(BaseTagLocalDataSource tagLocalDataSource) {
        allTagLiveData = new MutableLiveData<>();
        this.tagLocalDataSource = tagLocalDataSource;
        this.tagLocalDataSource.setBattleLogCallback(this);
    }

    public MutableLiveData<Result> fetchTag(long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //articleRemoteDataSource.getArticles(country);
            //get(apiService.getBattlelog(tagId));
        } else {
            tagLocalDataSource.getTag();
        }
        return allTagLiveData;
    }

    public MutableLiveData<Result> insertTag(Tag tagToInsert){
        tagLocalDataSource.insertTag(tagToInsert);
        return allTagLiveData;
    }

    public MutableLiveData<Result> deleteTag(Tag tagToDelete){
        tagLocalDataSource.deleteTag(tagToDelete);
        return allTagLiveData;
    }

    @Override
    public void onSuccessFromRemote(Tag tag, long lastUpdate) {
    }

    @Override
    public void onFailureFromRemote(String errorMessage) {
    }

    @Override
    public void onSuccessFromLocal(Tag tag) {
        Result result = new Result.Success(tag);
        allTagLiveData.postValue(result);
    }

    @Override
    public void onFailureFromLocal(Exception exception) {
        Result.Error resultError = new Result.Error(exception.getMessage());
        allTagLiveData.postValue(resultError);
    }
}
