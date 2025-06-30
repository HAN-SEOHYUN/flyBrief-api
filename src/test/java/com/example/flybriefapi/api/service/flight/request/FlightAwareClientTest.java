package com.example.flybriefapi.api.service.flight.request;

import com.example.flybriefapi.domain.flight.Flight;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import java.lang.reflect.Field;
import java.util.List;

@SpringBootTest
class FlightAwareClientTest {

    @DisplayName("FlightAwareRequest 정보를 기반으로 요청 URL을 생성한다")
    @Test
    void buildRequestUrl() throws Exception{
        // given
        String baseUrl = "https://aeroapi.flightaware.com/aeroapi";
        FlightAwareClient client = new FlightAwareClient(new RestTemplate());

        // 단위 테스트에서는 명시적으로 값 설정이 필요하므로 reflection으로 값을 세팅
        Field baseUrlField = FlightAwareClient.class.getDeclaredField("baseUrl");
        baseUrlField.setAccessible(true);
        baseUrlField.set(client, baseUrl);

        FlightAwareRequest request = new FlightAwareRequest(
                "ICN", "YVR", "2025-06-20", "2025-06-21"
        );

        // when
        String url = client.buildRequestUrl(request);

        // then
        assertThat(url).isEqualTo("https://aeroapi.flightaware.com/aeroapi/schedules/2025-06-20/2025-06-21?origin=ICN&destination=YVR");
    }

    @DisplayName("FlightAware API로부터 항공편 스케줄을 조회한다")
    @Test
    void getFlightSchedule() throws Exception {
        // given
        Dotenv dotenv = Dotenv.load();

        String baseUrl = "https://aeroapi.flightaware.com/aeroapi";
        String apiKey = dotenv.get("AERO_API_KEY");
        if (apiKey == null) throw new IllegalStateException("환경변수 AERO_API_KEY가 설정되지 않았습니다.");

        FlightAwareClient client = new FlightAwareClient(new RestTemplate());

        // reflection으로 baseUrl 주입
        Field baseUrlField = FlightAwareClient.class.getDeclaredField("baseUrl");
        baseUrlField.setAccessible(true);
        baseUrlField.set(client, baseUrl);

        // reflection으로 apiKey 주입
        Field apiKeyField = FlightAwareClient.class.getDeclaredField("apiKey");
        apiKeyField.setAccessible(true);
        apiKeyField.set(client, apiKey);

        FlightAwareRequest request = new FlightAwareRequest(
                "ICN", "YVR", "2025-06-20", "2025-06-21"
        );

        String requestUrl = client.buildRequestUrl(request);

        // when
        List<Flight> flights = client.getFlightSchedule(requestUrl);

        // then
        assertThat(flights).isNotNull();
        assertThat(flights.size()).isGreaterThan(0);

        //첫 번째 항공편의 출발 공항과 도착 공항 확인
        Flight first = flights.get(0);
        assertThat(first.getOrigin_iata()).isEqualToIgnoringCase("ICN");
        assertThat(first.getDestination_iata()).isEqualToIgnoringCase("YVR");
    }
}