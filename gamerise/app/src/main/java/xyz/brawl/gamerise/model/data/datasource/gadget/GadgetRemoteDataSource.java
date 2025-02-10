package xyz.brawl.gamerise.model.data.datasource.gadget;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.brawler.GadgetEntry;
import xyz.brawl.gamerise.model.data.player.PlayerApiResponse;
import xyz.brawl.gamerise.model.data.player.PlayerMapper;
import xyz.brawl.gamerise.model.service.ApiService;

public class GadgetRemoteDataSource extends BaseGadgetRemoteDataSource{
    private final ApiService apiService;
    public GadgetRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getGadgetList(String tagId) {
        tagId = "#" + tagId;
        apiService.getPlayer(tagId).enqueue(new Callback<PlayerApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlayerApiResponse> call, @NonNull Response<PlayerApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GadgetEntry> gadgetEntryList = PlayerMapper.mapToGadgets(response.body());
                    // Passa il risultato e il tempo di aggiornamento al callback
                    gadgetCallback.onSuccessFromRemote(gadgetEntryList);
                } else {
                    // Errore nel codice HTTP o risposta vuota
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    gadgetCallback.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PlayerApiResponse> call, @NonNull Throwable t) {
                // Errore di rete
                String errorMessage = "Errore di rete: " + t.getMessage();
                gadgetCallback.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
