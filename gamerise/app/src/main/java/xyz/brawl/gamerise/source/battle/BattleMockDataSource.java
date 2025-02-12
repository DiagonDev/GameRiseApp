package xyz.brawl.gamerise.source.battle;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.util.mappers.BattleMapper;
import xyz.brawl.gamerise.model.battle.BattleLogApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class BattleMockDataSource extends BaseBattleRemoteDataSource {
    private final JSONParserUtils jsonParserUtil;
    private static final String SAMPLE_JSON_FILENAME = "battlelog.json";

    public BattleMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    @Override
    public void getBattleLog(String tagId) {
        try {
            BattleLogApiResponse response = jsonParserUtil.parseJSONFileWithGSon(SAMPLE_JSON_FILENAME, BattleLogApiResponse.class);

            if (response != null && response.getBattleResponseList() != null) {
                List<Battle> battleList = BattleMapper.mapToBattles(response.getBattleResponseList());
                battleLogCallback.onSuccessFromRemote(battleList, System.currentTimeMillis());
            } else {
                battleLogCallback.onFailureFromRemote(new Exception("Errore: Risposta mock non valida"));
                Log.e("BattleMockDataSource", "Errore: Risposta mock non valida");
            }

        } catch (IOException e) {
            battleLogCallback.onFailureFromRemote(new Exception("Errore nel parsing del file JSON"));
            Log.e("BattleMockDataSource", "Errore nel parsing del file JSON", e);
        }
    }
}
