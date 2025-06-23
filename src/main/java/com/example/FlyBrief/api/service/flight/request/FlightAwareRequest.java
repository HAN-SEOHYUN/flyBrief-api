package com.example.FlyBrief.api.service.flight.request;

import lombok.Getter;

@Getter
public class FlightAwareRequest {
    private String origin;
    private String destination;
    private String startDate;
    private String endDate;

    public FlightAwareRequest(String origin, String destination, String startDate, String endDate) {
        this.origin = origin;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
