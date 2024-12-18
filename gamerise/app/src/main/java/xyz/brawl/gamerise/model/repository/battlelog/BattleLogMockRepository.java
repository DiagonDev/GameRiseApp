package xyz.brawl.gamerise.model.repository.battlelog;

import android.app.Application;
import android.content.Context;

import xyz.brawl.gamerise.util.JSONParserUtils;

@Deprecated // non usa AbstractRepository, se è da tenere e non è una classe "temporanea" al fine della lezione la farei astratta
public class BattleLogMockRepository implements IBattleLogRepository{
    private final Application application;

    public BattleLogMockRepository(Application application) {
        this.application = application;
    }

    @Override
    public void fetchBattleLog(String playerTag) {
        JSONParserUtils jsonParserUtils = new JSONParserUtils(application);
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
