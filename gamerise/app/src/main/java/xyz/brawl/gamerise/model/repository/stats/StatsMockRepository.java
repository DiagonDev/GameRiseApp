package xyz.brawl.gamerise.model.repository.stats;

import android.content.Context;

import java.io.IOException;

import xyz.brawl.gamerise.model.data.stat.api.StatsApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class StatsMockRepository {
    private Context context;

    public StatsMockRepository(Context context) { this.context = context; }

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
