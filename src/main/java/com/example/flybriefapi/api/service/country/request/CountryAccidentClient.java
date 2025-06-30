package com.example.flybriefapi.api.service.country.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class CountryAccidentClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${ACCIDENT_API_URL}")
    private String accidentApiUrl;

    @Value("${ACCIDENT_API_KEY}")
    private String serviceKey;

    public CountryAccidentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccidentNewsByIso3(String iso3) {
        URI uri = UriComponentsBuilder.fromHttpUrl(accidentApiUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", 1)
                .queryParam("pageNo", 1)
                .queryParam("isoCode1", iso3)
                .queryParam("_type", "json")  // ✅ 명시적으로 JSON 요청
                .build(true)
                .toUri();

        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return extractNewsFromJson(response.getBody());
        }

        return "뉴스 정보를 불러오는 데 실패했습니다.";
    }

    private String extractNewsFromJson(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            return root.path("response")
                    .path("body")
                    .path("items")
                    .path("item")
                    .path("news")
                    .asText("뉴스 정보가 없습니다.");
        } catch (Exception e) {
            return "뉴스 정보 파싱에 실패했습니다.";
        }
    }
}
