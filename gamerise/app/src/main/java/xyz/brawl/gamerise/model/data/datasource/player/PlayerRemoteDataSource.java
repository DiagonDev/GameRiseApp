package xyz.brawl.gamerise.model.data.datasource.player;

import android.content.Context;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.datasource.AbstractApiDataSource;
import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.util.ResponseCallback;

public class PlayerRemoteDataSource extends AbstractApiDataSource {
    public PlayerRemoteDataSource(Context context, ResponseCallback responseCallback) {
        super(context, responseCallback);
    }

    public void fetchPlayer(String playerTag) {
        get(apiService.getPlayer(playerTag));
    }
    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof Stat) {
            Stat stat = (Stat) response.body();

            //TODO: implement data base
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }

}
