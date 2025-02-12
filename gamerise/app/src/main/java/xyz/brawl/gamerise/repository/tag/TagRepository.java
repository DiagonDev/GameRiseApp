package xyz.brawl.gamerise.repository.tag;

import androidx.lifecycle.MutableLiveData;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.source.tag.BaseTagLocalDataSource;
import xyz.brawl.gamerise.model.tag.Tag;


public class TagRepository implements TagCallback {

    private final MutableLiveData<Result> allTagLiveData;
    private final BaseTagLocalDataSource tagLocalDataSource;

    public TagRepository(BaseTagLocalDataSource tagLocalDataSource) {
        allTagLiveData = new MutableLiveData<>();
        this.tagLocalDataSource = tagLocalDataSource;
        this.tagLocalDataSource.setBattleLogCallback(this);
    }

    public MutableLiveData<Result> fetchTag() {
        tagLocalDataSource.getTag();
        return allTagLiveData;
    }

    public void insertTag(Tag tagToInsert){
        tagLocalDataSource.insertTag(tagToInsert);
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
