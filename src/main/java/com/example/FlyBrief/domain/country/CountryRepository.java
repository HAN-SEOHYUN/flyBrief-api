package com.example.FlyBrief.domain.country;

import com.example.FlyBrief.domain.country.dao.CountryDao;
import com.example.FlyBrief.domain.country.dao.CountryDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository implements CountryDao {
    private final CountryDao countryDao;

    public CountryRepository(CountryDaoImpl countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country findByIso3(String iso3) {
        return countryDao.findByIso3(iso3);
    }
}
