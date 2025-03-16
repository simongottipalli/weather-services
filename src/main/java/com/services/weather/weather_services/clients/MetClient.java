package com.services.weather.weather_services.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.weather.weather_services.clients.models.MetResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
public class MetClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MetClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public MetResponse getExternalData(Map<String, String> params, Map<String, String> headers) throws Exception {
        String baseUrl = "https://api.met.no/weatherapi/locationforecast/2.0/compact";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create(baseUrl));
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        String url = builder.toUriString();// Set headers
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpHeaders.set(entry.getKey(), entry.getValue());
        }

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new Exception("HTTP Status Code: " + responseEntity.getStatusCode().value());
        }
            return objectMapper.readValue(responseEntity.getBody(), MetResponse.class);
    }


}

















