package xyz.brawl.gamerise.model.repository.player;

import android.app.Application;
import android.content.Context;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;

public class PlayerRepository extends AbstractRepository {

    public PlayerRepository(Application application, ResponseCallback responseCallback) {
        super(application, responseCallback);
    }

    //@Override
    public void fetchPlayer(String playerTag) {
        get(apiService.getPlayer(playerTag));
    }

    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof PlayerApiResponse) {
           responseCallback.onSuccess(response.body(), response.raw().receivedResponseAtMillis());
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }
}