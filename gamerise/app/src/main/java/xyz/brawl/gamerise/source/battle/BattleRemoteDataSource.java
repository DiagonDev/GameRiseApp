package xyz.brawl.gamerise.source.battle;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.model.battle.Battle;
import xyz.brawl.gamerise.util.mappers.BattleMapper;
import xyz.brawl.gamerise.model.battle.BattleLogApiResponse;
import xyz.brawl.gamerise.service.ApiService;

public class BattleRemoteDataSource extends BaseBattleRemoteDataSource {
    private final ApiService apiService;

    public BattleRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getBattleLog(String tagId) {
        tagId = "#" + tagId;
        apiService.getBattlelog(tagId).enqueue(new Callback<BattleLogApiResponse>() {

            @Override
            public void onResponse(@NonNull Call<BattleLogApiResponse> call, @NonNull Response<BattleLogApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Battle> battleList = BattleMapper.mapToBattles(response.body().getBattleResponseList());
                    battleLogCallback.onSuccessFromRemote(battleList, response.raw().receivedResponseAtMillis());
                }else{
                    String errorMessage = "Errore: Risposta non valida (Codice: " + response.code() + ")";
                    battleLogCallback.onFailureFromRemote(new Exception(errorMessage));
                    Log.e("BrawlerApiDataSource", errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BattleLogApiResponse> call, @NonNull Throwable t) {
                String errorMessage = "Errore di rete: " + t.getMessage();
                battleLogCallback.onFailureFromRemote(new Exception(errorMessage));
                Log.e("BrawlerApiDataSource", errorMessage);
            }
        });
    }
}
