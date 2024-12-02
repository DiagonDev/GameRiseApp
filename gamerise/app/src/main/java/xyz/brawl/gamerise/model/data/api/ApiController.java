package xyz.brawl.gamerise.model.data.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApiController {
    ApiClient ac;
    public ApiController() {
        ac = new ApiClient("sk8.fun:5223");
    }

    //TODO: i controlli andranno nei repository

    public boolean tagIsPlayer(String tag){
        if (isTag(tag)) {
            System.out.println("mo facciamo la query" + isTag(tag));
            return threadedNetworkRequest(tag) != null;
        }
        System.out.println("non è una tag");
        return false;
    }

    //TODO: isTag può finire in util
    public boolean isTag(String input) {
        if (!input.startsWith("#")) {
            input = "#" + input;
        }

        String regex = "#[A-Z0-9]+";

        return input.matches(regex);
    }


    public String threadedNetworkRequest(String endpoint) {
        String result = null;
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> ac.get(endpoint);

        Future<String> future = executorService.submit(task);

        try {
            result = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        return result;
    }

    public String getPlayer(String tag) {
        return threadedNetworkRequest("/players/%23" + tag);
    }
    public String getBattlelog (String playerTag){
        return threadedNetworkRequest( "/player/%23" + playerTag + "/battlelog");
    }
    public String getClubsLeaderboard(String countryCode) {
        return threadedNetworkRequest("/rankings/" + countryCode + "/clubs");
    }

    public String getBrawlersLeaderboard(String countryCode, int brawlerId) { // "i giocatori con più coppe su quel brawler"
        return threadedNetworkRequest("/rankings/" + countryCode + "/clubs/" + brawlerId);
    }

    public String getPlayersLeaderboard(String countryCode) {
        return threadedNetworkRequest("/rankings/" + countryCode + "/players");
    }
    public String getClubMembers (String clubTag) {
        return threadedNetworkRequest( "/clubs/%23" + clubTag + "/members");
    }

    public String getClub (String clubTag) {
        return threadedNetworkRequest( "/clubs/%23" + clubTag);
    }

    public String getBrawlerList () {
        return threadedNetworkRequest( "/brawlers");
    }

    public String getBrawler (int brawlerId){
        return threadedNetworkRequest( "/brawlers/" + brawlerId );
    }

    public String getEvents (){
        return threadedNetworkRequest("/events/rotation");
    }

}
