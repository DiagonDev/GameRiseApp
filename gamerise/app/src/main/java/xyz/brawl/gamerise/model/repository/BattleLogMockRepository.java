package xyz.brawl.gamerise.model.repository;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class BattleLogMockRepository {
    private Context context;

    public BattleLogMockRepository(Context context) {
        this.context = context;
    }

    void fetchBattleLog() {
        BattleLogApiResponse battleLogApiResponse = new BattleLogApiResponse();
        battleLogApiResponse = null;
        JSONParserUtils JSONParserUtils = new JSONParserUtils(context);
        try {
            battleLogApiResponse = JSONParserUtils.battleLogParseJSONWithGson("battlelog.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
