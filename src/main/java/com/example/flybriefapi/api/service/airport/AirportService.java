package com.example.flybriefapi.api.service.airport;

import com.example.flybriefapi.api.service.airport.response.AirportResponse;
import com.example.flybriefapi.domain.airport.Airport;
import com.example.flybriefapi.domain.airport.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportResponse> findAll(){
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(AirportResponse::of)
                .collect(Collectors.toList());
    }

    public List<AirportResponse> findByKeyword(String keyword){
        List<Airport> airports = airportRepository.findByKeyword(keyword);
        return airports.stream()
                .map(AirportResponse::of)
                .collect(Collectors.toList());
    }
}
