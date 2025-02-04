package xyz.brawl.gamerise.model.data.datasource.battle;

import static xyz.brawl.gamerise.model.data.battle.BattleMapper.mapToBattleLogEntries;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.data.battle.Battle;
import xyz.brawl.gamerise.model.data.battle.BattleMapper;
import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
import xyz.brawl.gamerise.model.data.brawler.BrawlerEntry;
import xyz.brawl.gamerise.model.data.brawler.BrawlerListResponse;
import xyz.brawl.gamerise.model.service.ApiService;

public class BattleRemoteDataSource extends BaseBattleRemoteDataSource {
    private final ApiService apiService;
    private final BattleMapper battlemapper = new BattleMapper();

    public BattleRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getBattleLog(String tagId) {
        tagId = "#" + tagId;
        apiService.getBattlelog(tagId).enqueue(new Callback<BattleLogApiResponse>() {

            @Override
            public void onResponse(Call<BattleLogApiResponse> call, Response<BattleLogApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Battle> battleList = battlemapper.mapToBattles(response.body().getBattleResponseList());
                    battleLogCallback.onSuccessFromRemote(battleList, response.raw().receivedResponseAtMillis());
                }else{
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    battleLogCallback.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(Call<BattleLogApiResponse> call, Throwable t) {
                String errorMessage = "Errore di rete: " + t.getMessage();
                battleLogCallback.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
