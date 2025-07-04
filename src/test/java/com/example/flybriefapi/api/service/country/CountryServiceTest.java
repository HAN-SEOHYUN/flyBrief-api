package com.example.flybriefapi.api.service.country;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryAccidentServiceTest {

    @Autowired
    private CountryService countryService;

    @DisplayName("공항 IATA 코드로 해당 나라의 사고/사건 뉴스 정보를 반환한다")
    @Test
    void getAccidentNewsByIataCode() {
        // given
        String iataCode = "BKK";

        // when
        String result = countryService.getAccidentNewsByIataCode(iataCode);

        // then
        assertThat(result)
                .isNotBlank(); // 빈 문자열이 아닌 경우 테스트 통과
        System.out.println(result);
    }
}
