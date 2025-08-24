package me.example.demo.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.example.demo.api.ApiExecutor;
import me.example.demo.api.ErApiExRateExtractor;
import me.example.demo.api.ExRateExtractor;
import me.example.demo.api.SimpleApiExecutor;
import me.example.demo.payment.ExRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {

        String url = "https://open.er-api.com/v6/latest/" + currency;
        return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        String response;
        try {
            uri = new URI( url );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            //response = new SimpleApiExecutor().execute(uri);
            response = apiExecutor.execute(uri);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 동작 -> 목적
    private static BigDecimal extractExRate(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ExRateDate data = mapper.readValue(response, ExRateDate.class);
        return data.rates().get("KRW");
    }


}
