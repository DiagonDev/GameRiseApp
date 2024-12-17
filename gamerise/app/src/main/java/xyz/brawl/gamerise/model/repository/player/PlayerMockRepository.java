package xyz.brawl.gamerise.model.repository.player;

import android.content.Context;

import java.io.IOException;

import xyz.brawl.gamerise.model.data.stat.Stat;
import xyz.brawl.gamerise.util.JSONParserUtils;

@Deprecated // non usa AbstractRepository, se è da tenere e non è una classe "temporanea" al fine della lezione la farei astratta
public class PlayerMockRepository {
    private Context context;

    public PlayerMockRepository(Context context) { this.context = context; }

    void fetchStats() {
        Stat stat = new Stat();
        stat = null;
        JSONParserUtils JSONParserUtils = new JSONParserUtils(context);
        try{
            stat = JSONParserUtils.statsParseJSONWithGson("stats.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
