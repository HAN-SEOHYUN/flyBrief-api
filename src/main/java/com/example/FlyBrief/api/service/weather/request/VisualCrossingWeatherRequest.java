package com.example.FlyBrief.api.service.weather.request;

import lombok.Getter;

@Getter
public class VisualCrossingWeatherRequest {

    private String unitGroup = "metric";
    private String include = "days";
    private String location;
    private String startDate;
    private String endDate;

    public VisualCrossingWeatherRequest(String location, String startDate, String endDate) {
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
