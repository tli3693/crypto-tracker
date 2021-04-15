package com.troy.crypto.tracker.service;

import com.troy.crypto.tracker.service.cmc.CMCResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoService {

    @Value("${coinmarketcap.url}")
    private String baseUrl;

    @Value("${coinmarketcap.common.coins}")
    private String commonCoins;

    private RestTemplate restTemplate;

    private HttpHeaders headers;

    public CryptoService(@Value("${coinmarketcap.apikey}") String apiKey) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("X-CMC_PRO_API_KEY", apiKey);
    }

    public String getLatestListings() {
        String resourceUrl = baseUrl + "/listings/latest";
        System.out.println("Making request to " + resourceUrl);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, request, String.class);
        return response.getBody();
    }

    public CMCResponse getCommonListings() {
        String resourceUrl = baseUrl + "/quotes/latest?symbol=" + commonCoins;
        System.out.println("Making request to " + resourceUrl);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        ResponseEntity<CMCResponse> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, request, CMCResponse.class);
        return response.getBody();
    }
}
