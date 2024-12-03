package com.solanaexchange.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
@Service
public class StockDataService {

    public Map<String,Object> getStockData(){
//        String url = "https://api.upstox.com/v2";
//        String url = "https://api.upstox.com/v2/historical-candle/NSE_EQ%7CINE848E01016/1minute/2023-11-13/2023-11-12";
//        Map<String,Object> stockMap = new HashMap<>();
//
//        RestTemplate restTemplate = new RestTemplate();
//        Map response = restTemplate.getForObject(url, Map.class);
//        stockMap.put("data", response);
//        return stockMap;

//            String url = "https://api.upstox.com/v2/historical-candle/NSE_EQ%7CINE848E01016/month/2023-11-19/2022-11-12";
            String url = "https://api.upstox.com/v2/historical-candle/NSE_EQ%7CINE848E01016/month/2023-11-19";
            HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();
        Map<String, Object> stockData = new HashMap<>();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            // Check the response status
            if (httpResponse.statusCode() == 200) {
                // Do something with the response body (e.g., print it)
                System.out.println(httpResponse.body());

                stockData.put("Data", httpResponse.body());

            } else {
                // Print an error message if the request was not successful
                System.err.println("Error: " + httpResponse.statusCode() + " - " + httpResponse.body());
                stockData.put("Error", httpResponse.body());
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return stockData;
    }
}
