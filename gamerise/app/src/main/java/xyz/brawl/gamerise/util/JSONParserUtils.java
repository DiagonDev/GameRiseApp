package xyz.brawl.gamerise.util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;

public class JSONParserUtils {
    public Context context;

    public JSONParserUtils(Context context) {
        this.context = context;
    }

    public BattleLogApiResponse parseJSONWithGson(String json) throws IOException {
        InputStream inputStream = context.getAssets().open(json);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return new Gson().fromJson(reader, BattleLogApiResponse.class);
    }
}
