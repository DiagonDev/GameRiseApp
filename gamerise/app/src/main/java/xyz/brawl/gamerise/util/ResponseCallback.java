package xyz.brawl.gamerise.util;

public interface ResponseCallback {
    //leo - primo parametro è Object perché se usiamo questo metodo per OGNI TIPO di Callback di ApiService
    //      abbiamo 275 tipi diversi di oggetti: List<>, ...Entry, ecc
    //      Object tira dentro tutto, si può poi castare alla classe giusta
    <T> void onSuccess(Object o, long lastUpdate);
    void onFailure(String errorMessage);
}
