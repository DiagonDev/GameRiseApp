package xyz.brawl.gamerise.repository.gadget;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.source.gadget.BaseGadgetLocalDataSource;
import xyz.brawl.gamerise.source.gadget.BaseGadgetRemoteDataSource;
import xyz.brawl.gamerise.util.GameAccountSingleton;

public class GadgetRepository implements GadgetCallback{
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

    public MutableLiveData<Result> fetchGadget(Long brawlerId, boolean connected, String tagId) {
        if (connected && !GameAccountSingleton.getInstance().isChecked()) {
            gadgetRemoteDataSource.getGadgetList(tagId);
        } else {
            gadgetLocalDataSource.getGadgets(brawlerId);
        }
        return allGadgetLiveData;
    }

    @Override
    public void onSuccessFromRemote(List<GadgetEntry> gadgetList) {
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
