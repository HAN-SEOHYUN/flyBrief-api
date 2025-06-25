package com.example.FlyBrief.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    private String datetime;       // 날짜 (예: "2025-06-28"), yyyy-MM-dd 형식
    private double tempmax;        // 일 최고 기온 (섭씨 ℃)
    private double tempmin;        // 일 최저 기온 (섭씨 ℃)
    private double feelslikemax;   // 체감 최고 기온 (섭씨 ℃)
    private double feelslikemin;   // 체감 최저 기온 (섭씨 ℃)
    private double precip;         // 총 강수량 (mm 단위)
    private List<String> preciptype;     // 강수 형태 (ex: 0=없음, 1=비, 2=눈 등) → ※ 원래는 String[]이지만 단순화 시 수치화 가능
    private String description;    // 날씨 설명 요약 (예: "Partly cloudy throughout the day.")
    private String icon;           // 날씨 아이콘 키 (예: "partly-cloudy-day", "clear-day")
    private double humidity;       // 평균 습도 (%)
    private double windGust;       // 최대 돌풍 속도 (km/h)
    private String sunrise;        // 일출 시각 (예: "05:09:47")
    private String sunset;         // 일몰 시각 (예: "21:21:52")


}
