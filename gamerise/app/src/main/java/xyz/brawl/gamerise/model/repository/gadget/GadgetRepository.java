package xyz.brawl.gamerise.model.repository.gadget;

import static xyz.brawl.gamerise.util.Constants.FRESH_TIMEOUT;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import xyz.brawl.gamerise.model.Result;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.datasource.gadget.BaseGadgetLocalDataSource;

public class GadgetRepository implements GadgetCallback{
    private final BaseGadgetLocalDataSource gadgetLocalDataSource;
    private final MutableLiveData<Result> allGadgetLiveData;

    public GadgetRepository(BaseGadgetLocalDataSource gadgetLocalDataSource) {
        allGadgetLiveData = new MutableLiveData<>();
        this.gadgetLocalDataSource = gadgetLocalDataSource;
        this.gadgetLocalDataSource.setGadgetCallback(this);
    }

    public MutableLiveData<Result> fetchGadget(Long brawlerId, long lastUpdate) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate > FRESH_TIMEOUT) {
            //Leo devi aggiungere qui i tuo metodo per recuperare i dati dal API
            //gadgetRemoteDataSource.getGadgets(brawlerId);
        } else {
            gadgetLocalDataSource.getGadgets(brawlerId);
        }
        return allGadgetLiveData;
    }

    @Override
    public void onSuccessFromLocal(List<GadgetEntry> gadgetList) {
        Result result = new Result.Success(gadgetList);
        allGadgetLiveData.postValue(result);
    }
}
