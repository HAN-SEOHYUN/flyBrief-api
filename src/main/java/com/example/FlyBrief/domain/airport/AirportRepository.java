package com.example.FlyBrief.domain.airport;

import com.example.FlyBrief.domain.airport.dao.AirportDao;
import com.example.FlyBrief.domain.airport.dao.AirportDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AirportRepository implements AirportDao {
    private final AirportDaoImpl airportDao;

    public AirportRepository(AirportDaoImpl airportDao) {
        this.airportDao = airportDao;
    }

    @Override
    public List<Airport> findAll() {
        return airportDao.findAll();
    }

    @Override
    public List<Airport> findByKeyword(String keyword) {
        return airportDao.findByKeyword(keyword);
    }
}
