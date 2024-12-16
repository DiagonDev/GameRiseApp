package xyz.brawl.gamerise.model.repository.player;

import android.content.Context;

import retrofit2.Response;
import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;
import xyz.brawl.gamerise.model.repository.AbstractRepository;
import xyz.brawl.gamerise.util.ResponseCallback;

public class PlayerRepository extends AbstractRepository implements IPlayerRepository {

    public PlayerRepository(Context context, ResponseCallback responseCallback) {
        super(context, responseCallback);
    }

    @Override
    public void fetchStats(String playerTag) {
        get(apiService.getPlayer(playerTag));
    }

    @Override
    protected <T> void handleApiResponse(Response<T> response) {
        if (response.body() instanceof StatsApiResponse) {
            StatsApiResponse statsApiResponse = (StatsApiResponse) response.body();
            int _3vs3Victories = statsApiResponse.get3vs3Victories();
            int trophies = statsApiResponse.getTrophies();
            int expLevel = statsApiResponse.getExpLevel();
            int highestTrophies = statsApiResponse.getHighestTrophies();
            int rank = statsApiResponse.getRank();
            int soloVictories = statsApiResponse.getSoloVictories();
            int duoVictories = statsApiResponse.getDuoVictories();
            //TODO: implement data base
        }
    }

    @Override
    protected void handleApiFailure(Throwable t) {

    }
}