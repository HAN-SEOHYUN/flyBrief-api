package com.example.flybriefapi.api.service.weather;

import com.example.flybriefapi.api.service.weather.request.WeatherSummaryClient;
import org.springframework.stereotype.Service;

@Service
public class WeatherSummaryService {
    private final WeatherSummaryClient weatherSummaryClient;

    public WeatherSummaryService(WeatherSummaryClient weatherSummaryClient) {
        this.weatherSummaryClient = weatherSummaryClient;
    }

    public String fetchWeatherSummary(String location, String startDate, String endDate) {
        return weatherSummaryClient.getWeatherSummary(location, startDate, endDate);
    }
}
