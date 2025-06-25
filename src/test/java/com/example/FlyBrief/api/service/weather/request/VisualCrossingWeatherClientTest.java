package com.example.FlyBrief.api.service.weather.request;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

@SpringBootTest
class VisualCrossingWeatherClientTest {

    @DisplayName("위치 및 날짜 정보를 기반으로 요청 URL을 생성한다 (쿼리 파라미터 제외)")
    @Test
    void buildRequestUrl() throws Exception {
        // given
        Dotenv dotenv = Dotenv.load();

        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
        String apiKey = dotenv.get("VISUALCROSSING_API_KEY");
        if (apiKey == null) throw new IllegalStateException("환경변수 VISUALCROSSING_API_KEY 가 설정되지 않았습니다.");

        VisualCrossingWeatherClient client = new VisualCrossingWeatherClient(new RestTemplate());

        // Reflection으로 private 필드 설정
        Field baseUrlField = VisualCrossingWeatherClient.class.getDeclaredField("baseUrl");
        baseUrlField.setAccessible(true);
        baseUrlField.set(client, baseUrl);

        Field apiKeyField = VisualCrossingWeatherClient.class.getDeclaredField("apiKey");
        apiKeyField.setAccessible(true);
        apiKeyField.set(client, apiKey);

        String location = "Vancouver";
        String startDate = "2025-06-28";
        String endDate = "2025-07-02";

        // when
        VisualCrossingWeatherRequest request = new VisualCrossingWeatherRequest(
                location,
                startDate,
                endDate
        );
        String url = client.buildRequestUrl(request);

        // 경로(path) 검증
        assertThat(url).startsWith(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Vancouver/2025-06-28/2025-07-02"
        );

        // query 포함 검증
        assertThat(url).contains("unitGroup=metric");
        assertThat(url).contains("key=");
        assertThat(url).contains("include=days");
    }
}