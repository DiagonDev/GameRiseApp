package xyz.brawl.gamerise.model.repository.tag;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.datasource.battle.BaseBattleLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.tag.BaseTagLocalDataSource;
import xyz.brawl.gamerise.model.data.tag.Tag;
import xyz.brawl.gamerise.model.repository.tag.TagCallback;
import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Response;
import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;



public class TagRepository implements TagCallback {
    //private static final String TAG = TagRepository.class.getSimpleName();

    private final MutableLiveData<Result> allTagLiveData;
    private final BaseTagLocalDataSource tagLocalDataSource;

    public TagRepository(BaseTagLocalDataSource tagLocalDataSource) {

        allTagLiveData = new MutableLiveData<>();
        this.tagLocalDataSource = tagLocalDataSource;
        this.tagLocalDataSource.setBattleLogCallback(this);
    }

    public MutableLiveData<Result> getRecentTags(String tagId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //articleRemoteDataSource.getArticles(country);
            //get(apiService.getBattlelog(tagId));
        } else {
            tagLocalDataSource.getRecentTags();
        }
        return allTagLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<Tag> tags, long lastUpdate) {

    }

    @Override
    public void onFailureFromRemote(String errorMessage) {

    }

    @Override
    public void onSuccessFromLocal(List<Tag> tags) {

    }

    @Override
    public void onFailureFromLocal(Exception exception) {

    }
}
