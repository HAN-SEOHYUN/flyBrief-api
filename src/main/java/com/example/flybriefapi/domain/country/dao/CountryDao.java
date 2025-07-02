package com.example.flybriefapi.domain.country.dao;

import com.example.flybriefapi.domain.country.Country;

public interface CountryDao {
    Country findByIataCode(String iataCode);
}
