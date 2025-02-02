package xyz.brawl.gamerise.model;

import xyz.brawl.gamerise.model.data.battle.api.BattleLogApiResponse;
public abstract class Result {
    private Result() {}

    public boolean isSuccess() { return !(this instanceof Error); }

    // Successo generico per qualsiasi tipo di dato
    public static final class Success extends Result {
        private final Object o;
        public Success(Object o) {
            this.o = o;}
        public Object getData() {
            return o;}
    }

    // Errore con messaggio generico
    public static final class Error extends Result {
        private final String message;
        public Error(String message) {
            this.message = message;}
        public String getMessage() {
            return message;}}
}

