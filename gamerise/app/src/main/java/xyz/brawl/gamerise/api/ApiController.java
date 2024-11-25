package xyz.brawl.gamerise.api;

public class ApiController {
    ApiClient ac;
    public ApiController() {
        ac = new ApiClient("sk8.fun:35710");
    }
    public boolean tagIsPlayer(String tag){
        if (isTag(tag)) {
            System.out.println("mo facciamo la query" + isTag(tag));
            return endpointPlayers(tag) != null;
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

    public String endpointPlayers(String tag) {
        try {
            String endpoint = "players/" + "%23" + tag;
                    String response = ac.get(endpoint);
            System.out.println("endpoint: " + endpoint + "\nresponse: " + response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
