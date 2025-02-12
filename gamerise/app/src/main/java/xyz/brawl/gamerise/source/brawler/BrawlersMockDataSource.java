package xyz.brawl.gamerise.source.brawler;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import xyz.brawl.gamerise.model.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;
import xyz.brawl.gamerise.util.JSONParserUtils;

public class BrawlersMockDataSource extends BaseBrawlersRemoteDataSource {
    private final JSONParserUtils jsonParserUtil;
    private static final String SAMPLE_JSON_FILENAME = "players.json";

    public BrawlersMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    @Override
    public void getBrawlerList(String tagId) {
        try {
            PlayerApiResponse response = jsonParserUtil.parseJSONFileWithGSon(SAMPLE_JSON_FILENAME, PlayerApiResponse.class);

            if (response != null) {
                List<BrawlerEntry> brawlerList = PlayerMapper.mapToBrawlers(response);
                brawlersCallBack.onSuccessFromRemote(brawlerList, System.currentTimeMillis());
            } else {
                brawlersCallBack.onFailureFromRemote(new Exception("Errore: Risposta mock non valida"));
                Log.e("BrawlerMockDataSource", "Errore: Risposta mock non valida");
            }
        } catch (IOException e) {
            brawlersCallBack.onFailureFromRemote(new Exception("Errore nel parsing del file JSON"));
            Log.e("BrawlerMockDataSource", "Errore nel parsing del file JSON", e);
        }
    }
}
