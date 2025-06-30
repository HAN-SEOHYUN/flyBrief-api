package com.example.flybriefapi.api.service.airport;

import com.example.flybriefapi.api.service.airport.response.AirportResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
class AirportServiceTest {
    @Autowired
    private AirportService airportService;

    @DisplayName("IATA 코드로 검색할 경우 해당 공항을 반환한다")
    @Test
    void findByKeywordIataCode() {
        //given
        String iataCode = "YVR";

        // when
        List<AirportResponse> result = airportService.findByKeyword(iataCode);

        // then
        assertThat(result).hasSize(1)
                .extracting("airportNameKr")
                .containsExactly("밴쿠버 국제 공항");
    }

    @DisplayName("영문 공항명으로 검색할 경우 해당 공항(들)을 반환한다")
    @Test
    void findByKeywordAirportNameEn() {
        //given
        String airportNameEn = "International";

        // when
        List<AirportResponse> result = airportService.findByKeyword(airportNameEn);

        // then
        assertThat(result).hasSizeGreaterThanOrEqualTo(2);
    }

    @DisplayName("한글 공항명으로 검색할 경우 해당 공항을 반환한다")
    @Test
    void findByKeywordKrAirportNameKr() {
        //given
        String airportNameKr = "밴쿠버 국제";

        // when
        List<AirportResponse> result = airportService.findByKeyword(airportNameKr);

        // then
        assertThat(result).hasSize(1)
                .extracting("airportNameEn")
                .containsExactly("Vancouver International Airport");
    }
}