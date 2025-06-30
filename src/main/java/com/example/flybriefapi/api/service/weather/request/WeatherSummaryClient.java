package com.example.flybriefapi.api.service.weather.request;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherSummaryClient {
    private final RestTemplate restTemplate;

    @Value("${WEATHER_SUMMARY_URL}")
    private String flaskUrl;

    public WeatherSummaryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeatherSummary(String location, String startDate, String endDate) {
        // 요청 바디 구성
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("location", location);
        requestBody.put("startDate", startDate);
        requestBody.put("endDate", endDate);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP Entity 생성
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // 요청 전송
        ResponseEntity<Map> response = restTemplate.postForEntity(
                flaskUrl,
                entity,
                Map.class
        );

        // 응답 파싱
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("message");
        }

        return "날씨 정보를 불러오는 데 실패했습니다.";
    }
}
