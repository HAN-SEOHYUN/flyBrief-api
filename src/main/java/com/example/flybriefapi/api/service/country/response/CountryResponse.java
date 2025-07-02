package com.example.flybriefapi.api.service.country.response;

import com.example.flybriefapi.domain.country.Country;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CountryResponse {
    private String iso3;                 // 국가 ISO3 코드
    private String iso2;                 // 국가 ISO2 코드
    private String countryNameKo;        // 국가명 (한글)
    private String countryNameEn;        // 국가명 (영문)

    private String attention;            // 여행유의
    private String attentionPartial;     // 여행유의(일부)
    private String attentionNote;        // 여행유의 내용

    private String control;              // 여행자제
    private String controlPartial;       // 여행자제(일부)
    private String controlNote;          // 여행자제 내용

    private String limita;               // 출국권고
    private String limitaPartial;        // 출국권고(일부)
    private String limitaNote;           // 출국권고 내용

    private String banYna;               // 여행금지
    private String banYnPartial;         // 여행금지(일부)
    private String banNote;              // 여행금지 내용

    private String wrtDt;                // 작성일 (정보 기준일)

    private String capital;              // 수도
    private String population;           // 인구 수
    private String populationDesc;       // 인구 관련 설명

    private String areaKm2;              // 면적 (km²)
    private String areaDesc;             // 면적 관련 설명

    private String language;             // 공용어
    private String religion;             // 주요 종교
    private String ethnicity;            // 주요 민족
    private String climate;              // 기후
    private String independence;         // 독립일 또는 독립 여부

    @Builder
    public CountryResponse(String iso3, String iso2, String countryNameKo, String countryNameEn, String attention, String attentionPartial, String attentionNote, String control, String controlPartial, String controlNote, String limita, String limitaPartial, String limitaNote, String banYna, String banYnPartial, String banNote, String wrtDt, String capital, String population, String populationDesc, String areaKm2, String areaDesc, String language, String religion, String ethnicity, String climate, String independence) {
        this.iso3 = iso3;
        this.iso2 = iso2;
        this.countryNameKo = countryNameKo;
        this.countryNameEn = countryNameEn;
        this.attention = attention;
        this.attentionPartial = attentionPartial;
        this.attentionNote = attentionNote;
        this.control = control;
        this.controlPartial = controlPartial;
        this.controlNote = controlNote;
        this.limita = limita;
        this.limitaPartial = limitaPartial;
        this.limitaNote = limitaNote;
        this.banYna = banYna;
        this.banYnPartial = banYnPartial;
        this.banNote = banNote;
        this.wrtDt = wrtDt;
        this.capital = capital;
        this.population = population;
        this.populationDesc = populationDesc;
        this.areaKm2 = areaKm2;
        this.areaDesc = areaDesc;
        this.language = language;
        this.religion = religion;
        this.ethnicity = ethnicity;
        this.climate = climate;
        this.independence = independence;
    }

    public static CountryResponse of(Country country) {
        return CountryResponse.builder()
                .iso3(country.getIso3())
                .iso2(country.getIso2())
                .countryNameKo(country.getCountryNameKo())
                .countryNameEn(country.getCountryNameEn())
                .attention(country.getAttention())
                .attentionPartial(country.getAttentionPartial())
                .attentionNote(country.getAttentionNote())
                .control(country.getControl())
                .controlPartial(country.getControlPartial())
                .controlNote(country.getControlNote())
                .limita(country.getLimita())
                .limitaPartial(country.getLimitaPartial())
                .limitaNote(country.getLimitaNote())
                .banYna(country.getBanYna())
                .banYnPartial(country.getBanYnPartial())
                .banNote(country.getBanNote())
                .wrtDt(country.getWrtDt())
                .capital(country.getCapital())
                .population(country.getPopulation())
                .populationDesc(country.getPopulationDesc())
                .areaKm2(country.getAreaKm2())
                .areaDesc(country.getAreaDesc())
                .language(country.getLanguage())
                .religion(country.getReligion())
                .ethnicity(country.getEthnicity())
                .climate(country.getClimate())
                .independence(country.getIndependence())
                .build();
    }
}
