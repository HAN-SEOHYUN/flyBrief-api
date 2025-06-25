package com.example.FlyBrief.api.controller.weather;

import com.example.FlyBrief.api.ApiResponse;
import com.example.FlyBrief.api.service.weather.WeatherService;
import com.example.FlyBrief.api.service.weather.WeatherSummaryService;
import com.example.FlyBrief.api.service.weather.response.WeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherSummaryService weatherSummaryService;

    public WeatherController(WeatherService weatherService, WeatherSummaryService weatherSummaryService) {
        this.weatherService = weatherService;
        this.weatherSummaryService = weatherSummaryService;
    }

    @GetMapping("/forecast")
    public ApiResponse<List<WeatherResponse>> getWeatherForecast(
            @RequestParam String location,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        List<WeatherResponse> results = weatherService.getWeatherForecast(location, startDate, endDate);
        return ApiResponse.ok(results);
    }

    @GetMapping("/summary")
    public Map<String, String> getWeatherSummary(
            @RequestParam String location,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        String message = weatherSummaryService.fetchWeatherSummary(location, startDate, endDate);
        return Map.of("message", message);
    }
}
