package com.example.flybriefapi.api.controller.flight;

import com.example.flybriefapi.api.ApiResponse;
import com.example.flybriefapi.api.service.flight.FlightService;
import com.example.flybriefapi.api.service.flight.response.FlightResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/schedules")
    public ApiResponse<List<FlightResponse>> getFlightSchedules(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        List<FlightResponse> results = flightService.getScheduledFlights(origin, destination, startDate, endDate);
        return ApiResponse.ok(results);
    }
}

