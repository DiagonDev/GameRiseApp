package xyz.brawl.gamerise.model.data.datasource.brawler;

import android.accounts.NetworkErrorException;

import xyz.brawl.gamerise.model.data.api.ApiClient;
import xyz.brawl.gamerise.model.data.datasource.AbstractRemoteDataSource;

public class BrawlerRemoteDataSource extends AbstractRemoteDataSource {

    protected BrawlerRemoteDataSource(ApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Recupera tutti brawlers dall'API REST.
     *
     * @return Lista di brawlers.
     * @throws NetworkErrorException in caso di errore di rete o server.
     */
    public String getBrawlerList() throws NetworkErrorException {
        return threadedNetworkRequest("/brawlers");
    }

    /**
     * Recupera un brawler specifico dall'API REST.
     *
     * @return Brawler specifico.
     * @throws NetworkErrorException in caso di errore di rete o server.
     */
    public String getBrawler(int brawlerId) throws NetworkErrorException {
        return threadedNetworkRequest("/brawlers/" + brawlerId);
    }
}
