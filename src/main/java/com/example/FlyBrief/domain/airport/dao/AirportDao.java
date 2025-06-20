package com.example.FlyBrief.domain.airport.dao;

import com.example.FlyBrief.domain.airport.Airport;

import java.util.List;

public interface AirportDao {
    List<Airport> findAll();
    List<Airport> findByKeyword(String keyword);
}
