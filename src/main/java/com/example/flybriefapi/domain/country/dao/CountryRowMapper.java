package com.example.flybriefapi.domain.country.dao;

import com.example.flybriefapi.domain.country.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Country.builder()
                .iso3(rs.getString("iso3"))
                .countryNameKo(rs.getString("country_name_ko"))
                .countryNameEn(rs.getString("country_name_en"))
                .attention(rs.getString("attention"))
                .attentionPartial(rs.getString("attention_partial"))
                .attentionNote(rs.getString("attention_note"))
                .control(rs.getString("control"))
                .controlPartial(rs.getString("control_partial"))
                .controlNote(rs.getString("control_note"))
                .limita(rs.getString("limita"))
                .limitaPartial(rs.getString("limita_partial"))
                .limitaNote(rs.getString("limita_note"))
                .banYna(rs.getString("ban_yna"))
                .banYnPartial(rs.getString("ban_yn_partial"))
                .banNote(rs.getString("ban_note"))
                .wrtDt(rs.getString("wrt_dt"))
                .iso2(rs.getString("iso2"))
                .capital(rs.getString("capital"))
                .population(rs.getString("population"))
                .populationDesc(rs.getString("population_desc"))
                .areaKm2(rs.getString("area_km2"))
                .areaDesc(rs.getString("area_desc"))
                .language(rs.getString("language"))
                .religion(rs.getString("religion"))
                .ethnicity(rs.getString("ethnicity"))
                .climate(rs.getString("climate"))
                .independence(rs.getString("independence"))
                .build();
    }
}
