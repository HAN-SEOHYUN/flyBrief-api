package com.example.flybriefapi.domain.country.dao;

import com.example.flybriefapi.domain.country.Country;

public interface CountryDao {
    Country findByIso3(String iso3);
}
