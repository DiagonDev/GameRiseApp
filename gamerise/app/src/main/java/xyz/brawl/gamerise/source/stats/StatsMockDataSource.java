package xyz.brawl.gamerise.source.stats;

import android.util.Log;

import java.io.IOException;

import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;

public class StatsMockDataSource extends BaseStatsRemoteDataSource {

    private final JSONParserUtils jsonParserUtil;
    private static final String SAMPLE_JSON_FILENAME = "players.json";

    public StatsMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    public void getStats(String tagId) {
        try {
            PlayerApiResponse response = jsonParserUtil.parseJSONFileWithGSon(SAMPLE_JSON_FILENAME, PlayerApiResponse.class);

            if (response != null) {
                statsCallBack.onSuccessFromRemote(PlayerMapper.mapToStat(response), System.currentTimeMillis());
            } else {
                statsCallBack.onFailureFromRemote(new Exception("Errore: Risposta mock non valida"));
                Log.e("StatsMockDataSource", "Errore: Risposta mock non valida");
            }

        } catch (IOException e) {
            statsCallBack.onFailureFromRemote(new Exception("Errore nel parsing del file JSON"));
            Log.e("StatsMockDataSource", "Errore nel parsing del file JSON", e);
        }
    }
}
