package com.example.FlyBrief.api.controller.airport;

import com.example.FlyBrief.api.ApiResponse;
import com.example.FlyBrief.api.service.airport.AirportService;
import com.example.FlyBrief.api.service.airport.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    @GetMapping("/")
    public ApiResponse<List<AirportResponse>> getAllAirports() {
        return ApiResponse.ok(airportService.findAll());
    }

    @GetMapping("/search")
    public ApiResponse<List<AirportResponse>> searchAirports(@RequestParam String keyword) {
        List<AirportResponse> results = airportService.findByKeyword(keyword);
        return ApiResponse.ok(results);
    }
}
