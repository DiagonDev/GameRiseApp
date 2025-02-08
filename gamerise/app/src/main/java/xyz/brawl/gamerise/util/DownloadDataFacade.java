package xyz.brawl.gamerise.util;


/**
 * Applicazione pattern Façade
 * Questa classe incapsula tutti i metodi relativi alla logica di fetch da API e di salvataggio nel database
 * Verrà chiamata dai ViewModel e userà i metodi dei repository
 */
public class DownloadDataFacade  {

    /**
     * Step 1: Esegue fetch del player per il tag corrente
     * Step 2: Salva il tag nel database se non presente
     * Step 3: La fetch chiama il metodi del responseCallback per salvare i dati nel database
     * Step 4: Esegue fetch della battlelog per il tag corrente
     * Step 5: come Step 3 ma con le stats, brawlers, gadget e starpowers
     * Step 6: Carico la tabella Owns nel database
     */

    public void saveData(String tag, boolean save) {

    }
}

