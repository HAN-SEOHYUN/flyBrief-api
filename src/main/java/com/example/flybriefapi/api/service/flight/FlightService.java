package com.example.flybriefapi.api.service.flight;

import com.example.flybriefapi.api.service.flight.request.FlightAwareClient;
import com.example.flybriefapi.api.service.flight.request.FlightAwareRequest;
import com.example.flybriefapi.api.service.flight.response.FlightResponse;
import com.example.flybriefapi.domain.flight.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightAwareClient flightAwareClient;

    public FlightService(FlightAwareClient flightAwareClient) {
        this.flightAwareClient = flightAwareClient;
    }

    public List<FlightResponse> getScheduledFlights(String origin, String destination, String startDate, String endDate) {
        FlightAwareRequest request = new FlightAwareRequest(origin, destination, startDate, endDate);
        String url = flightAwareClient.buildRequestUrl(request);
        List<Flight> flights = flightAwareClient.getFlightSchedule(url);

        return flights.stream()
                .map(FlightResponse::of)
                .collect(Collectors.toList());
    }
}
