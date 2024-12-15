package xyz.brawl.gamerise.model.repository.battlelog;

import android.content.Context;

import xyz.brawl.gamerise.util.JSONParserUtils;

@Deprecated // non usa AbstractRepository, se è da tenere e non è una classe "temporanea" al fine della lezione la farei astratta
public class BattleLogMockRepository implements IBattleLogRepository{
    private Context context;

    public BattleLogMockRepository(Context context) {
        this.context = context;
    }

    @Override
    public void fetchBattleLog(String playerTag) {
        JSONParserUtils jsonParserUtils = new JSONParserUtils(context);
        /*
        try {
            // Usa il file JSON corretto
            return jsonParserUtils.battleLogParseJSONWithGson("battlelogsimpled.json");
        } catch (IOException e) {
            // Log dell'errore (puoi usare il tuo logger preferito)
            e.printStackTrace();

            // Restituisci un valore predefinito o `null` in caso di errore
            return null;
        }*/
    }

}
