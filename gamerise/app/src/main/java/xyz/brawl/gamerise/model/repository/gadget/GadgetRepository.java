package xyz.brawl.gamerise.model.repository.gadget;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.datasource.gadget.BaseGadgetLocalDataSource;
import xyz.brawl.gamerise.model.data.datasource.gadget.BaseGadgetRemoteDataSource;

public class GadgetRepository implements GadgetCallback {
    private final BaseGadgetLocalDataSource gadgetLocalDataSource;
    private final BaseGadgetRemoteDataSource gadgetRemoteDataSource;
    private final MutableLiveData<Result> allGadgetLiveData;

    public GadgetRepository(BaseGadgetLocalDataSource gadgetLocalDataSource, BaseGadgetRemoteDataSource gadgetRemoteDataSource) {
        allGadgetLiveData = new MutableLiveData<>();
        this.gadgetLocalDataSource = gadgetLocalDataSource;
        this.gadgetRemoteDataSource = gadgetRemoteDataSource;
        this.gadgetLocalDataSource.setGadgetCallback(this);
        this.gadgetRemoteDataSource.setGadgetCallback(this);
    }

    public MutableLiveData<Result> insertGadgets(List<GadgetEntry> gadgetEntryList, Long brawlerId) {
        gadgetLocalDataSource.insertGadgets(gadgetEntryList, brawlerId);
        return allGadgetLiveData;
    }

    public MutableLiveData<Result> fetchGadget(Long brawlerId, long lastUpdate, String tagId) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            gadgetRemoteDataSource.getGadgetList(tagId);
        } else {
            gadgetLocalDataSource.getGadgets(brawlerId);
        }
        return allGadgetLiveData;
    }

    @Override
    public void onSuccessFromRemote(Object gadgetList, long lastUpdate) {
        Result result = new Result.Success(gadgetList);
        allGadgetLiveData.postValue(result);
    }

    @Override
    public void onFailureFromRemote(Exception errorMessage) {
        Result.Error resultError = new Result.Error(errorMessage.getMessage());
        allGadgetLiveData.postValue(resultError);
    }

    @Override
    public void onSuccessFromLocal(List<GadgetEntry> gadgetList) {
        Result result = new Result.Success(gadgetList);
        allGadgetLiveData.postValue(result);
    }
}
