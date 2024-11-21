package xyz.brawl.gamerise.ui.recyclers.tag;

public class Tag {
    String nomeGiocatore;
    String tag;

    public Tag(String nomeGiocatore, String tag) {
        this.nomeGiocatore = nomeGiocatore;
        this.tag = tag;
    }

    public String getNomeGiocatore() {
        return nomeGiocatore;
    }

    public String getTag() {
        return tag;
    }
}
