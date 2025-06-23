package com.example.FlyBrief.api.service.flight.request;

import com.example.FlyBrief.domain.flight.Flight;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

@Component
public class FlightAwareClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${AERO_API_KEY}")
    private String apiKey;

    @Value("${AEROAPI_BASE_URL}")
    private String baseUrl;

    public FlightAwareClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 요청 URL 빌드
    public String buildRequestUrl(FlightAwareRequest request) {
        return String.format(
                "%s/schedules/%s/%s?origin=%s&destination=%s",
                baseUrl,
                request.getStartDate(),
                request.getEndDate(),
                request.getOrigin(),
                request.getDestination()
        );
    }

    // FlightAware 항공스케줄 조회 API 호출
    public List<Flight> getFlightSchedule(String requestUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apikey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers); // 요청 헤더 포함된 HttpEntity 객체 생성

        ResponseEntity<String> response = restTemplate.exchange(
                requestUrl,
                HttpMethod.GET,
                entity,
                String.class // 응답을 문자열로 받아옴
        );

        return parseScheduledFlights(response.getBody());
    }

    // Flight 객체 리스트 형태로 파싱
    private List<Flight> parseScheduledFlights(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode scheduledNode = root.path("scheduled");

            if (scheduledNode.isArray()) {
                return objectMapper.readerForListOf(Flight.class).readValue(scheduledNode);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 등 처리
            return Collections.emptyList();
        }
    }
}
