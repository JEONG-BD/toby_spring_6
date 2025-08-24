package me.example.demo.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.example.demo.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/";
        String response;
        URI uri = null;

        try {
            uri = new URI( url + currency);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            response = executeApi(uri);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        try {
            return parseExrate(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal parseExrate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateDate data = mapper.readValue(response, ExRateDate.class);
        return data.rates().get("KRW");
    }

    private static String executeApi(URI uri) throws IOException {
        String response;
        HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
            response = br.lines().collect(Collectors.joining());
        }
        return response;
    }
}
