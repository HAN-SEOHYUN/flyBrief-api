package com.example.flybriefapi.api.service.travelAgent.request;

import com.example.flybriefapi.api.service.travelAgent.response.TravelAgentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class TravelAgentClient {

    private final RestTemplate restTemplate;

    @Value("${TRAVEL_RECOMMENDATION_URL}")
    private String travelRecommendationUrl;

    public TravelAgentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TravelAgentResponse getRecommendations(String travelTheme) {
        // 요청 바디 구성
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("travelTheme", travelTheme);

        // 헤더 구성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP Entity 생성
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        // 요청 전송
        ResponseEntity<Map> response = restTemplate.postForEntity(
                travelRecommendationUrl,
                entity,
                Map.class
        );

        // 응답 파싱
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> body = response.getBody();
            String message = (String) body.getOrDefault("message", "추천 메시지가 없습니다.");
            List<String> iso3Codes = (List<String>) body.getOrDefault("iso3", Collections.emptyList());
            return new TravelAgentResponse(message, iso3Codes);
        }

        return new TravelAgentResponse("추천 정보를 불러오는 데 실패했습니다.", Collections.emptyList());
    }


}
