package com.example.flybriefapi.domain.flight;
import lombok.Getter;

@Getter
public class Flight {

    private String ident; // 항공편 식별자 (예: SIA1002)
    private String ident_icao; // ICAO 형식 식별자
    private String ident_iata; // IATA 형식 식별자

    private String actual_ident; // 실제 운항 항공편 식별자 (공동 운항일 경우)
    private String actual_ident_icao; // ICAO 형식의 실제 항공편 식별자
    private String actual_ident_iata; // IATA 형식의 실제 항공편 식별자

    private String aircraft_type; // 항공기 종류 (보통 ICAO 코드 기준)
    private String scheduled_in; // 예정 도착 시간 (게이트 기준, UTC 기준 ISO 형식)
    private String scheduled_out; // 예정 출발 시간 (게이트 기준, UTC 기준 ISO 형식)

    private String origin; // 출발 공항 코드
    private String origin_icao; // ICAO 형식의 도착 공항 코드
    private String origin_iata; // IATA 형식의 출발 공항 코드
    private String origin_lid; // LID 형식의 출발 공항 코드

    private String destination; // 도착 공항 코드
    private String destination_icao; // ICAO 형식의 도착 공항 코드
    private String destination_iata; // IATA 형식의 도착 공항 코드
    private String destination_lid; // LID 형식의 도착 공항 코드

    private String fa_flight_id; // FlightAware 고유 항공편 ID (예: ACA64-1750...); 먼 미래의 일정일 경우 null일 수 있음

    private String meal_service; // 기내식 정보 (예: "Business: Meal / Economy: Snack")

    private int seats_cabin_business; // 비즈니스 클래스 좌석 수
    private int seats_cabin_coach; // 이코노미 클래스 좌석 수
    private int seats_cabin_first; // 퍼스트 클래스 좌석 수


}
