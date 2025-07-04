package com.example.flybriefapi.api.controller.country;

import com.example.flybriefapi.api.ApiResponse;
import com.example.flybriefapi.api.service.country.CountryService;
import com.example.flybriefapi.api.service.country.response.CountryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/info")
    public ApiResponse<CountryResponse> getCountryInfo(@RequestParam String iataCode){
        CountryResponse result = countryService.findByIataCode(iataCode);
        return ApiResponse.ok(result);
    }

    @GetMapping("/news")
    public ApiResponse<String> getCountryAccidentNews(@RequestParam String iataCode){
        String result = countryService.getAccidentNewsByIataCode(iataCode);
        return ApiResponse.ok(result);
    }
}
