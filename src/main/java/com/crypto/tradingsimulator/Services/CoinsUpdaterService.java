package com.crypto.tradingsimulator.Services;

import com.crypto.tradingsimulator.Repositories.CoinsRepository;
import com.crypto.tradingsimulator.models.currencies.Coin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@Service
public class CoinsUpdaterService {

    private final static String API_ENDPOINT = "https://api.coinlore.com/api/tickers/";

    private final HttpClient HTTP_CLIENT = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL).proxy(ProxySelector.getDefault()).build();

    private final HttpResponse.BodyHandler<String> asString = HttpResponse.BodyHandlers.ofString();

    private final CoinsRepository coinsRepository;

    @Autowired
    public CoinsUpdaterService(CoinsRepository coinsRepository) {
        this.coinsRepository = coinsRepository;
    }

    @Scheduled(fixedRate = 300000)
    public void updateCoinsData() {
        try {
            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(API_ENDPOINT))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .build();

            var httpResponse = HTTP_CLIENT.send(httpRequest, asString);

            coinsRepository.saveAll(parseCoinsJson(httpResponse.body()));

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Cannot get HTTP response from client: ", e);
        }
    }

    private List<Coin> parseCoinsJson(String data) {
        JSONObject jsonObj = new JSONObject(data);
        JSONArray arrayWithCoins = jsonObj.getJSONArray("data");
        return new Gson().fromJson(arrayWithCoins.toString(), new TypeToken<List<Coin>>() {
        }.getType());
    }
}


