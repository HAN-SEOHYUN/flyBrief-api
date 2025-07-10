package com.example.flybriefapi.api.service.travelAgent;

import com.example.flybriefapi.api.service.travelAgent.request.TravelAgentClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TravelAgentClientTest {

    @Autowired
    private TravelAgentClient travelAgentClient;

    @DisplayName("여행 테마를 보내면 message와 iso3Codes 필드를 포함한 응답을 받는다")
    @Test
    void getRecommendations_returnsMessageAndIso3List() {
        // given
        String travelTheme = "바다";

        // when
        TravelAgentClient.TravelRecommendationResponse response =
                travelAgentClient.getRecommendations(travelTheme);

        // then
        assertThat(response.message()).isNotBlank();
        assertThat(response.iso3Codes()).isInstanceOfAny(java.util.List.class);
    }
}
