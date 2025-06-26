package com.example.FlyBrief.domain.country.dao;

import com.example.FlyBrief.domain.country.Country;

public interface CountryDao {
    Country findByIso3(String iso3);
}
