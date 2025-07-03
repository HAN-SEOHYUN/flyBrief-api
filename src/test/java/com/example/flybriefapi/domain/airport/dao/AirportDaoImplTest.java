package com.example.flybriefapi.domain.airport.dao;

import com.example.flybriefapi.domain.airport.AirportRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AirportDaoImplTest {

    @Autowired
    private AirportRepository airportRepository;

    @DisplayName("IATA 코드로 도시명을 조회하면 해당 도시명이 반환된다")
    @Test
    void findCityNameByIataCode() {
        // given
        String iataCode = "YVR"; //밴쿠버

        // when
        String cityName = airportRepository.findCityNameByIataCode(iataCode);

        // then
        assertThat(cityName).isEqualTo("Vancouver");
    }
}