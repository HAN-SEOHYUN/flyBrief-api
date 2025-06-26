package com.example.FlyBrief.api.controller.country;

import com.example.FlyBrief.api.ApiResponse;
import com.example.FlyBrief.api.service.country.CountryService;
import com.example.FlyBrief.api.service.country.response.CountryResponse;
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
    public ApiResponse<CountryResponse> getCountryInfo(@RequestParam String iso3){
        CountryResponse result = countryService.findByIso3(iso3);
        return ApiResponse.ok(result);
    }
}
