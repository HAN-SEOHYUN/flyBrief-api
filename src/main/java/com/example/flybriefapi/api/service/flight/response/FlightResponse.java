package com.example.flybriefapi.api.service.flight.response;

import com.example.flybriefapi.domain.flight.Flight;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class FlightResponse {

    private String airlineCodeIata;
    private String departureAirportIataCode;
    private String departureAirportNameKr;

    private String arrivalAirportIataCode;
    private String arrivalAirportNameKr;
    private String aircraftType;

    private String scheduledArrivalDate;
    private String scheduledArrivalTime;
    private String scheduledDepartureDate;
    private String scheduledDepartureTime;

    private String mealService;
    private String flightDuration;

    @Builder
    public FlightResponse(
            String airlineCodeIata,
            String departureAirportIataCode,
            String departureAirportNameKr,
            String arrivalAirportIataCode,
            String arrivalAirportNameKr,
            String aircraftType,
            String scheduledArrivalDateTime,     // ISO 형식 입력
            String scheduledDepartureDateTime,   // ISO 형식 입력
            String mealService,
            String flightDuration
    ) {
        this.airlineCodeIata = airlineCodeIata;
        this.departureAirportIataCode = departureAirportIataCode;
        this.departureAirportNameKr = departureAirportNameKr;
        this.arrivalAirportIataCode = arrivalAirportIataCode;
        this.arrivalAirportNameKr = arrivalAirportNameKr;
        this.aircraftType = aircraftType;

        this.scheduledDepartureDate = parseDate(scheduledDepartureDateTime);
        this.scheduledDepartureTime = parseTime(scheduledDepartureDateTime);
        this.scheduledArrivalDate = parseDate(scheduledArrivalDateTime);
        this.scheduledArrivalTime = parseTime(scheduledArrivalDateTime);

        this.mealService = mealService;
        this.flightDuration = flightDuration;
    }

    private String parseDate(String isoDateTimeString) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(isoDateTimeString, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return ""; // 혹은 로그 후 빈 문자열 반환
        }
    }

    private String parseTime(String isoDateTimeString) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.parse(isoDateTimeString, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return "";
        }
    }

    private static String calculateDuration(String departure, String arrival) {
        try {
            ZonedDateTime dep = ZonedDateTime.parse(departure, DateTimeFormatter.ISO_DATE_TIME);
            ZonedDateTime arr = ZonedDateTime.parse(arrival, DateTimeFormatter.ISO_DATE_TIME);
            Duration duration = Duration.between(dep, arr);

            long hours = duration.toHours();
            long minutes = duration.toMinutesPart(); // Java 9+

            return String.format("%d시간 %02d분", hours, minutes);
        } catch (Exception e) {
            return "";
        }
    }

    public static FlightResponse of(Flight flight) {
        return FlightResponse.builder()
                .airlineCodeIata(flight.getIdent_iata())
                .departureAirportIataCode(flight.getOrigin_iata())
                .departureAirportNameKr("") // 공항 한글명 매핑은 추후 적용
                .arrivalAirportIataCode(flight.getDestination_iata())
                .arrivalAirportNameKr("") // 공항 한글명 매핑은 추후 적용
                .aircraftType(flight.getAircraft_type())
                .scheduledArrivalDateTime(flight.getScheduled_in())
                .scheduledDepartureDateTime(flight.getScheduled_out())
                .mealService(flight.getMeal_service())
                .flightDuration(calculateDuration(flight.getScheduled_out(), flight.getScheduled_in())) // ✅ 여기 적용
                .build();
    }
}
