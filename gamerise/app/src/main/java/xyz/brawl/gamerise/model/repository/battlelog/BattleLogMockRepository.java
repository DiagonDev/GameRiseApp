package xyz.brawl.gamerise.model.repository.battlelog;

import android.content.Context;

import java.io.IOException;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class BattleLogMockRepository implements IBattleLogRepository{
    private Context context;

    public BattleLogMockRepository(Context context) {
        this.context = context;
    }

    @Override
    public BattleLogApiResponse fetchBattleLog(String playerTag, long lastUpdate) {
        JSONParserUtils jsonParserUtils = new JSONParserUtils(context);

        try {
            // Usa il file JSON corretto
            return jsonParserUtils.battleLogParseJSONWithGson("battlelogsimpled.json");
        } catch (IOException e) {
            // Log dell'errore (puoi usare il tuo logger preferito)
            e.printStackTrace();

            // Restituisci un valore predefinito o `null` in caso di errore
            return null;
        }
    }

}
