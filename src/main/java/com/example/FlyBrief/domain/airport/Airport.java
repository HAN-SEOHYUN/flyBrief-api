package com.example.FlyBrief.domain.airport;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Airport {
    private int id;
    private String iataCode;
    private String airportNameEn; //공항명 (영문)
    private String airportNameKr; //공항명 (한글)

    @Builder
    public Airport(int id, String iataCode, String airportNameEn, String airportNameKr) {
        this.id = id;
        this.iataCode = iataCode;
        this.airportNameEn = airportNameEn;
        this.airportNameKr = airportNameKr;
    }
}
