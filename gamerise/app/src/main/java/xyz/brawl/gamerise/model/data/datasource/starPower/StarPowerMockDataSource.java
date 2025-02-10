package xyz.brawl.gamerise.model.data.datasource.starPower;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import xyz.brawl.gamerise.model.data.brawler.StarPowerEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class StarPowerMockDataSource extends BaseStarPowerRemoteDataSource {
    private final JSONParserUtils jsonParserUtil;
    private static final String SAMPLE_JSON_FILENAME = "players.json";

    public StarPowerMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    @Override
    public void getStarPowerList(String tagId) {
        try {
            PlayerApiResponse response = jsonParserUtil.parseJSONFileWithGSon(SAMPLE_JSON_FILENAME, PlayerApiResponse.class);

            if (response != null) {
                List<StarPowerEntry> starPowerEntryList = PlayerMapper.mapToStarPowers(response);
                starPowerCallback.onSuccessFromRemote(starPowerEntryList);
            } else {
                starPowerCallback.onFailureFromRemote(new Exception("Errore: Risposta mock non valida"));
                Log.e("StarPowerMockDataSource", "Errore: Risposta mock non valida");
            }

        } catch (IOException e) {
            starPowerCallback.onFailureFromRemote(new Exception("Errore nel parsing del file JSON"));
            Log.e("StarPowerMockDataSource", "Errore nel parsing del file JSON", e);
        }
    }
}
