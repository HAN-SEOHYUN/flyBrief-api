package com.example.flybriefapi.api.service.weather;

import com.example.flybriefapi.domain.airport.AirportRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


import com.example.flybriefapi.api.service.weather.request.WeatherSummaryClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class WeatherSummaryServiceTest {

    @Test
    void fetchWeatherSummary_containsCityNameInResponse() {
        // given
        String iataCode = "CDG"; // 파리 샤를 드골 공항
        String startDate = "2025-07-01";
        String endDate = "2025-07-02";
        String cityName = "Paris";
        String fakeSummary = "Weather summary for Paris: mostly sunny.";

        AirportRepository airportRepository = mock(AirportRepository.class);
        WeatherSummaryClient weatherSummaryClient = mock(WeatherSummaryClient.class);
        WeatherSummaryService service = new WeatherSummaryService(weatherSummaryClient, airportRepository);

        when(airportRepository.findCityNameByIataCode(iataCode)).thenReturn(cityName);
        when(weatherSummaryClient.getWeatherSummary(cityName, startDate, endDate)).thenReturn(fakeSummary);

        // when
        String result = service.fetchWeatherSummary(iataCode, startDate, endDate);

        // then
        assertThat(result).containsIgnoringCase("paris");
    }
}