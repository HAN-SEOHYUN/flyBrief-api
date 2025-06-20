package com.example.FlyBrief.api.service.airport.response;

import com.example.FlyBrief.domain.airport.Airport;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AirportResponse {

    private int id;
    private String iataCode;
    private String airportNameEn;
    private String airportNameKr;

    @Builder
    public AirportResponse(int id, String iataCode, String airportNameEn, String airportNameKr) {
        this.id = id;
        this.iataCode = iataCode;
        this.airportNameEn = airportNameEn;
        this.airportNameKr = airportNameKr;
    }

    public static AirportResponse of(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .airportNameEn(airport.getAirportNameEn())
                .airportNameKr(airport.getAirportNameKr())
                .build();
    }
}
