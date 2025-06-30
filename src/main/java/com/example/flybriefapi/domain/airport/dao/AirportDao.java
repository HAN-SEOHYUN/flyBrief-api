package com.example.flybriefapi.domain.airport.dao;

import com.example.flybriefapi.domain.airport.Airport;

import java.util.List;

public interface AirportDao {
    List<Airport> findAll();
    List<Airport> findByKeyword(String keyword);
}
