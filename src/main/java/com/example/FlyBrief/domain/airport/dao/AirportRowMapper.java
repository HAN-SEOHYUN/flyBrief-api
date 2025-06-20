package com.example.FlyBrief.domain.airport.dao;

import com.example.FlyBrief.domain.airport.Airport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportRowMapper implements RowMapper<Airport>{
    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Airport.builder()
                .id(rs.getInt("id"))
                .iataCode(rs.getString("iata_code"))
                .airportNameEn(rs.getString("airport_name_en"))
                .airportNameKr(rs.getString("airport_name_ko"))
                .build();
    }
}
