package com.example.FlyBrief.api.service.weather;

import com.example.FlyBrief.api.service.weather.request.VisualCrossingWeatherClient;
import com.example.FlyBrief.api.service.weather.request.VisualCrossingWeatherRequest;
import com.example.FlyBrief.api.service.weather.response.WeatherResponse;
import com.example.FlyBrief.domain.weather.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final VisualCrossingWeatherClient visualCrossingWeatherClient;

    public WeatherService(VisualCrossingWeatherClient visualCrossingWeatherClient) {
        this.visualCrossingWeatherClient = visualCrossingWeatherClient;
    }

    public List<WeatherResponse> getWeatherForecast(String location, String startDate, String endDate) {
        VisualCrossingWeatherRequest request = new VisualCrossingWeatherRequest(
                location, startDate, endDate
        );

        String url = visualCrossingWeatherClient.buildRequestUrl(request);
        List<Weather> weathers = visualCrossingWeatherClient.getWeatherForecast(url);

        return weathers.stream()
                .map(WeatherResponse::of)
                .collect(Collectors.toList());
    }
}
