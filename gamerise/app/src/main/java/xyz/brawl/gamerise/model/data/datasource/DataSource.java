package xyz.brawl.gamerise.model.data.datasource;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Response;

public interface DataSource {
    <T> void get(@NonNull Call<T> call);
    <T> void handleApiResponse(Response<T> response);
    void handleApiFailure(Throwable t);
}
