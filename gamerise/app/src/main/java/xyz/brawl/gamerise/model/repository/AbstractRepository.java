package xyz.brawl.gamerise.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.brawl.gamerise.R;
import xyz.brawl.gamerise.model.service.ApiService;
import xyz.brawl.gamerise.model.service.ServiceLocator;
import xyz.brawl.gamerise.util.ResponseCallback;

public abstract class AbstractRepository {
    protected ApiService apiService;
    protected Context context;
    protected ResponseCallback responseCallback;

    public AbstractRepository(Context context, ResponseCallback responseCallback) {
        this.apiService = ServiceLocator.getInstance().getApiService();
        this.context = context;
        this.responseCallback = responseCallback;
    }


    public <T> void get(@NonNull Call<T> call) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> c, @NonNull Response<T> response) {
                //TODO: fare dei check sul codice di risposta
                // esempio: if (response.code() == 200) la risposta va bene
                //          if (response.code() != 200) errore di risposta
                // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
                if (response.body() != null && response.isSuccessful() && response.code() == 200) {
                    handleApiResponse(response);
                } else {
                    // qui finiamo se la request è andata a buon fine, MA il codice di risposta non è 200
                    // esempio: la tag non è valida o non esiste
                    responseCallback.onFailure(context.getString(R.string.error_message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> c, @NonNull Throwable t) {
                handleApiFailure(t);
                responseCallback.onFailure(t.getMessage());
                Log.d("TAG", "NO RESPONSE");
            }
        });
    }

    // metodi da implementare nei Repository, se si vuole fare qualcosa della `response` o del `throwable`
    protected abstract <T> void handleApiResponse(Response<T> response);
    protected abstract void handleApiFailure(Throwable t);
}
