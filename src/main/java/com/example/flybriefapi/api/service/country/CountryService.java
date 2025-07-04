package com.example.flybriefapi.api.service.country;

import com.example.flybriefapi.api.service.country.request.CountryAccidentClient;
import com.example.flybriefapi.api.service.country.response.CountryResponse;
import com.example.flybriefapi.domain.airport.AirportRepository;
import com.example.flybriefapi.domain.country.Country;
import com.example.flybriefapi.domain.country.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryAccidentClient countryAccidentClient;
    private final AirportRepository airportRepository;

    public CountryService(CountryRepository countryRepository, CountryAccidentClient countryAccidentClient, AirportRepository airportRepository) {
        this.countryRepository = countryRepository;
        this.countryAccidentClient = countryAccidentClient;
        this.airportRepository = airportRepository;
    }

    public CountryResponse findByIataCode(String iataCode) {
        Country country = countryRepository.findByIataCode(iataCode);
        if (country == null) {
            throw new NoSuchElementException("해당 국가 정보를 찾을 수 없습니다: " + iataCode);
        }
        return CountryResponse.of(country);
    }

    public String getAccidentNewsByIataCode(String iataCode) {
        String iso3 = airportRepository.findIso3ByIataCode(iataCode);
        return countryAccidentClient.getAccidentNewsByIso3(iso3);
    }
}
