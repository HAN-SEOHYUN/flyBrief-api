package com.example.flybriefapi.api.service.weather;

import com.example.flybriefapi.api.service.weather.request.VisualCrossingWeatherClient;
import com.example.flybriefapi.api.service.weather.request.VisualCrossingWeatherRequest;
import com.example.flybriefapi.api.service.weather.response.WeatherResponse;
import com.example.flybriefapi.domain.airport.AirportRepository;
import com.example.flybriefapi.domain.weather.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    private final VisualCrossingWeatherClient visualCrossingWeatherClient;
    private final AirportRepository airportRepository;

    public WeatherService(VisualCrossingWeatherClient visualCrossingWeatherClient, AirportRepository airportRepository) {
        this.visualCrossingWeatherClient = visualCrossingWeatherClient;
        this.airportRepository = airportRepository;
    }

    public List<WeatherResponse> getWeatherForecast(String iataCode, String startDate, String endDate) {
        String location = airportRepository.findCityNameByIataCode(iataCode);

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
