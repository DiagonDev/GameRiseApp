package xyz.brawl.gamerise.util;

import android.app.Application;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import xyz.brawl.gamerise.model.battle.BattleLogApiResponse;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;

public class JSONParserUtils {
    private final Application application;

    public JSONParserUtils(Application application) {
        this.application = application;
    }

    public <T> T parseJSONFileWithGSon(String filename, Class<T> clazz) throws IOException {
        InputStream inputStream = application.getAssets().open(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return new Gson().fromJson(bufferedReader, clazz);
    }
}
