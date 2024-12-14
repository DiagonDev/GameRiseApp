package xyz.brawl.gamerise.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.service.ApiService;
import xyz.brawl.gamerise.model.service.ServiceLocator;
import xyz.brawl.gamerise.util.ResponseCallback;

public class AbstractRepository {
    protected ApiService apiService;
    protected Context context;
    protected ResponseCallback responseCallback;

    public AbstractRepository(Context context, ResponseCallback responseCallback) {
        this.apiService = ServiceLocator.getInstance().getApiService();
        this.context = context;
        this.responseCallback = responseCallback;
    }

    // se riuscissimo ad astrarre questo metodo sarebbe top così non fa reimplementata la logica di base in ogni classe
    // + il codice generico<T> è un flex if you ask me
    public <T> void get(Call<T> call) {
        call.enqueue(new Callback<T>() { // async call
            @Override
            public void onResponse(@NonNull Call<T> c, @NonNull Response<T> response) {
                //TODO: cambiare messaggio di risposta
                if (response.body() != null && response.isSuccessful() && response.message().equals("OK")) {
                    // fatto a naso siccome BattleLogRepository non fa handling di sta roba, l'ho scritto guardando quello
                    responseCallback.onSuccess((List<T>) response.body(), response.raw().receivedResponseAtMillis());
                }
                else {
                    responseCallback.onFailure(context.getString(R.string.error_message));
                    Log.d("TAG", "NO RESPONSE");
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> c, @NonNull Throwable t) {
                responseCallback.onFailure(t.getMessage());
                Log.d("TAG", "NO RESPONSE");
            }
        });
    }
}
