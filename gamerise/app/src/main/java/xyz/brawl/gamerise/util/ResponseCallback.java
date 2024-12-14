package xyz.brawl.gamerise.util;

import java.util.List;



public interface ResponseCallback {
    <T> void onSuccess(List<T> list, long lastUpdate);
    void onFailure(String errorMessage);
}
