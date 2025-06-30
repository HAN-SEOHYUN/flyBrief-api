package com.example.flybriefapi.api.service.weather.request;

import com.example.flybriefapi.domain.weather.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.List;

@Component
public class VisualCrossingWeatherClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${VISUALCROSSING_API_KEY}")
    private String apiKey;

    @Value("${VISUALCROSSING_BASE_URL}")
    private String baseUrl;

    public VisualCrossingWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buildRequestUrl(VisualCrossingWeatherRequest request) {
        String path = String.format("%s/%s/%s/%s", baseUrl, request.getLocation(), request.getStartDate(), request.getEndDate());

        return UriComponentsBuilder.fromHttpUrl(path)
                .queryParam("unitGroup", "metric")
                .queryParam("key", apiKey)
                .queryParam("include", "days")
                .build()
                .toUriString();
    }

    public List<Weather> getWeatherForecast(String requestUrl) {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                entity,
                String.class
        );

        return parseWeatherData(response.getBody());
    }

    // Weather 객체 리스트 형태로 파싱
    private List<Weather> parseWeatherData(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode daysNode = root.path("days");

            if (daysNode.isArray()) {
                return objectMapper.readerForListOf(Weather.class).readValue(daysNode);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace(); // TODO: 로깅 처리
            return Collections.emptyList();
        }
    }

}
