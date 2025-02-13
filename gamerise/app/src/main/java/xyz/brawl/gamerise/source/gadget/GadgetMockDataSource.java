package xyz.brawl.gamerise.source.gadget;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import xyz.brawl.gamerise.model.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.player.PlayerApiResponse;
import xyz.brawl.gamerise.util.JSONParserUtils;
import xyz.brawl.gamerise.util.mappers.PlayerMapper;

public class GadgetMockDataSource extends BaseGadgetRemoteDataSource {
    private final JSONParserUtils jsonParserUtil;
    private static final String SAMPLE_JSON_FILENAME = "players.json";

    public GadgetMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    @Override
    public void getGadgetList(String tagId) {
        try {
            PlayerApiResponse response = jsonParserUtil.parseJSONFileWithGSon(SAMPLE_JSON_FILENAME, PlayerApiResponse.class);

            if (response != null) {
                List<GadgetEntry> gadgetEntryList = PlayerMapper.mapToGadgets(response);
                gadgetCallback.onSuccessFromRemote(gadgetEntryList);
            } else {
                gadgetCallback.onFailureFromRemote(new Exception("Errore: Risposta mock non valida"));
                Log.e("GadgetMockDataSource", "Errore: Risposta mock non valida");
            }

        } catch (IOException e) {
            gadgetCallback.onFailureFromRemote(new Exception("Errore nel parsing del file JSON"));
            Log.e("GadgetMockDataSource", "Errore nel parsing del file JSON", e);
        }
    }
}
