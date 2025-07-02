package com.example.flybriefapi.domain.country;

import com.example.flybriefapi.domain.country.dao.CountryDao;
import com.example.flybriefapi.domain.country.dao.CountryDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository implements CountryDao {
    private final CountryDao countryDao;

    public CountryRepository(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country findByIataCode(String iataCode) {
        return countryDao.findByIataCode(iataCode);
    }
}
