package com.example.flybriefapi.api.service.weather;

import com.example.flybriefapi.api.service.weather.request.WeatherSummaryClient;
import com.example.flybriefapi.domain.airport.AirportRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherSummaryService {
    private final WeatherSummaryClient weatherSummaryClient;
    private final AirportRepository airportRepository;

    public WeatherSummaryService(WeatherSummaryClient weatherSummaryClient, AirportRepository airportRepository) {
        this.weatherSummaryClient = weatherSummaryClient;
        this.airportRepository = airportRepository;
    }

    public String fetchWeatherSummary(String iataCode, String startDate, String endDate) {
        String location = airportRepository.findCityNameByIataCode(iataCode);

        return weatherSummaryClient.getWeatherSummary(location, startDate, endDate);
    }
}
