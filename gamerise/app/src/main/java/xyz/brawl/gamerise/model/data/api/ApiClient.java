package xyz.brawl.gamerise.model.data.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    private String baseUrl = "";
    private int connectTimeout = 10000;
    private int readTimeout = 10000;
    private static final String HTTPS = "https://";

    ApiClient(String baseUrl, int connectionTimeout, int readTimeout) {
        if (!baseUrl.startsWith(HTTPS)) this.baseUrl = "HTTPS" + baseUrl;
        else this.baseUrl = baseUrl;
        this.connectTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
    }

    ApiClient(String baseUrl) {
        if (!baseUrl.startsWith(HTTPS))
            baseUrl = HTTPS + baseUrl;
        this.baseUrl = baseUrl;
    }

    // String response = apiClient.get("/endpoint"));
    // https://developer.brawlstars.com/#/documentation
    public String get(String endpoint) throws Exception {
        System.out.println(baseUrl + endpoint);
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);

        int responseCode = connection.getResponseCode();
        return handleResponse(connection, responseCode);
    }

    private String handleResponse(HttpURLConnection connection, int responseCode) throws Exception {
        BufferedReader in;
        if (responseCode >= 200 && responseCode < 300)
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        else
            in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();
        return response.toString();
    }
}
