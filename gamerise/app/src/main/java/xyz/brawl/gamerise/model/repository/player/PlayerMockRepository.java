package xyz.brawl.gamerise.model.repository.player;

import android.content.Context;

import java.io.IOException;

import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;

@Deprecated // non usa AbstractRepository, se è da tenere e non è una classe "temporanea" al fine della lezione la farei astratta
public class PlayerMockRepository {
    private Context context;

    public PlayerMockRepository(Context context) { this.context = context; }

    void fetchStats() {
        StatsApiResponse statsApiResponse = new StatsApiResponse();
        statsApiResponse = null;
        JSONParserUtils JSONParserUtils = new JSONParserUtils(context);
        try{
            statsApiResponse = JSONParserUtils.statsParseJSONWithGson("stats.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
