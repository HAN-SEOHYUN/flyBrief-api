package com.example.flybriefapi.api.service.weather.response;

import com.example.flybriefapi.domain.weather.Weather;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WeatherResponse {

    private String dateTime;      // 예보 날짜 (형식: yyyy-MM-dd)
    private double tempMax;       // 일 최고 기온 (섭씨 ℃)
    private double tempMin;       // 일 최저 기온 (섭씨 ℃)
    private double feelsLikeMax;  // 체감 최고 기온 (섭씨 ℃)
    private double feelsLikeMin;  // 체감 최저 기온 (섭씨 ℃)
    private double precip;        // 강수량 (mm) - 해당 일자의 누적 예상 강수량
    private String description;   // 날씨 요약 설명 (예: "Partly cloudy throughout the day.")
    private String icon;          // 날씨 상태 아이콘 키워드 (예: "rain", "clear-day", "partly-cloudy-day")
    private double humidity;      // 평균 습도 (%) - 해당 일자의 평균 상대 습도
    private double windGust;      // 최대 돌풍 속도 (km/h) - 해당 일자의 예상 최대 순간풍속
    private String sunrise;       // 일출 시각 (예: "05:10:00") - 현지 시간 기준
    private String sunset;        // 일몰 시각 (예: "21:20:00") - 현지 시간 기준

    @Builder
    public WeatherResponse(String dateTime, double tempMax, double tempMin, double feelsLikeMax, double feelsLikeMin, double precip, String description, String icon, double humidity, double windGust, String sunrise, String sunset) {
        this.dateTime = dateTime;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.feelsLikeMax = feelsLikeMax;
        this.feelsLikeMin = feelsLikeMin;
        this.precip = precip;
        this.description = description;
        this.icon = icon;
        this.humidity = humidity;
        this.windGust = windGust;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public static WeatherResponse of(Weather weather) {
        return WeatherResponse.builder()
                .dateTime(weather.getDatetime())
                .tempMax(weather.getTempmax())
                .tempMin(weather.getTempmin())
                .feelsLikeMax(weather.getFeelslikemax())
                .feelsLikeMin(weather.getFeelslikemin())
                .precip(weather.getPrecip())
                .description(weather.getDescription())
                .icon(weather.getIcon())
                .humidity(weather.getHumidity())
                .sunrise(weather.getSunrise())
                .sunset(weather.getSunset())
                .build();
    }
}
