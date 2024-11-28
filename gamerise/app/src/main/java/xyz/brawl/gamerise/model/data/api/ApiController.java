package xyz.brawl.gamerise.model.data.api;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import kotlin.jvm.internal.Ref;

public class ApiController {
    ApiClient ac;
    public ApiController() {
        ac = new ApiClient("sk8.fun:5223");
    }
    public boolean tagIsPlayer(String tag){
        if (isTag(tag)) {
            System.out.println("mo facciamo la query" + isTag(tag));
            return threadedNetworkRequest(tag) != null;
        }
        System.out.println("non è una tag");
        return false;
    }

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

        Callable<String> task = () -> {
            return ac.get(endpoint);
        };

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

    public String battlelog (String tag){
        return threadedNetworkRequest( "/player/%23" + tag + "/battlelog");
    }

    public String members (String clubTag){
        return threadedNetworkRequest( "/clubs/%23" + clubTag + "/members");
    }

    public String clubs (String clubTag){
        return threadedNetworkRequest( "/clubs/%23" + clubTag);
    }

    public String listBrawlers (){
        return threadedNetworkRequest( "/brawlers");
    }

    public String brawlersID (int brawlerId){
        return threadedNetworkRequest( "/brawlers/%23" + brawlerId );
    }

    public String events (){
        return threadedNetworkRequest("/events/rotation");
    }

}
