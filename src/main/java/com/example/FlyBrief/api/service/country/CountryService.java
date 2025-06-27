package com.example.FlyBrief.api.service.country;

import com.example.FlyBrief.api.service.country.request.CountryAccidentClient;
import com.example.FlyBrief.api.service.country.response.CountryResponse;
import com.example.FlyBrief.domain.country.Country;
import com.example.FlyBrief.domain.country.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryAccidentClient countryAccidentClient;

    public CountryService(CountryRepository countryRepository, CountryAccidentClient countryAccidentClient) {
        this.countryRepository = countryRepository;
        this.countryAccidentClient = countryAccidentClient;
    }

    public CountryResponse findByIso3(String iso3) {
        Country country = countryRepository.findByIso3(iso3);
        if (country == null) {
            throw new NoSuchElementException("해당 국가 정보를 찾을 수 없습니다: " + iso3);
        }
        return CountryResponse.of(country);
    }

    public String getAccidentNewsByIso3 (String iso3) {
        return countryAccidentClient.getAccidentNewsByIso3(iso3);
    }
}
