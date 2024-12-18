package xyz.brawl.gamerise.util;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.stat.Stat;

public class JSONParserUtils {
    private final Application application;

    public JSONParserUtils(Application application) {
        this.application = application;
    }

    public BattleLogApiResponse battleLogParseJSONWithGson(String json) throws IOException {
        InputStream inputStream = application.getAssets().open(json);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return new Gson().fromJson(reader, BattleLogApiResponse.class);
    }

    public PlayerApiResponse statsParseJSONWithGson(String json) throws IOException {
        InputStream inputStream = application.getAssets().open(json);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return new Gson().fromJson(reader, PlayerApiResponse.class);
    }
}
