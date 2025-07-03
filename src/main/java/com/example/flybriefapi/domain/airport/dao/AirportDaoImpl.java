package com.example.flybriefapi.domain.airport.dao;

import com.example.flybriefapi.domain.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirportDaoImpl implements AirportDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AirportDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Airport> findAll() {
        String sql = """
           SELECT id, airport_name_en, airport_name_ko FROM FlyBrief.airport_info;
           """;
        return jdbcTemplate.query(sql,new AirportRowMapper());
    }

    public List<Airport> findByKeyword(String keyword) {
        String sql = """
            SELECT id, iata_code, airport_name_en, airport_name_ko 
            FROM FlyBrief.airport_info
            WHERE airport_name_ko COLLATE utf8mb4_general_ci LIKE CONCAT('%', ?, '%')
               OR airport_name_en COLLATE utf8mb4_general_ci LIKE CONCAT('%', ?, '%')
               OR iata_code COLLATE utf8mb4_general_ci LIKE CONCAT('%', ?, '%')
               OR country_name_ko COLLATE utf8mb4_general_ci LIKE CONCAT('%', ?, '%')
               OR country_name_en COLLATE utf8mb4_general_ci LIKE CONCAT('%', ?, '%')
            ;
        """;
        return jdbcTemplate.query(sql, new AirportRowMapper(), keyword, keyword, keyword, keyword, keyword);
    }

    @Override
    public String findCityNameByIataCode(String iataCode) {
        String sql = """
            SELECT city_name_en
            FROM airport_info
            WHERE iata_code = ?
            """;
        try {
            return jdbcTemplate.queryForObject(sql, String.class, iataCode);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}


