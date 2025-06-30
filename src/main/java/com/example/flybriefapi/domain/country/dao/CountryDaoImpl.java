package com.example.flybriefapi.domain.country.dao;

import com.example.flybriefapi.domain.country.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Country findByIso3(String iso3) {
        String sql = """
                SELECT
                    ca.iso3,                        -- 국가 코드 (ISO 3자리)
                    ca.country_name_ko,            -- 국가명 (한글)
                    ca.country_name_en,            -- 국가명 (영문)
                    ca.attention,                  -- 여행유의
                    ca.attention_partial,          -- 여행유의(일부)
                    ca.attention_note,             -- 여행유의 내용
                    ca.control,                    -- 여행자제
                    ca.control_partial,            -- 여행자제(일부)
                    ca.control_note,               -- 여행자제 내용
                    ca.limita,                     -- 출국권고
                    ca.limita_partial,            -- 출국권고(일부)
                    ca.limita_note,                -- 출국권고 내용
                    ca.ban_yna,                    -- 여행금지
                    ca.ban_yn_partial,             -- 여행금지(일부)
                    ca.ban_note,                   -- 여행금지 내용
                    ca.wrt_dt,                     -- 등록일
                    ci.iso2,                       -- 국가 코드 (ISO 2자리)
                    ci.iso3 AS ci_iso3,           -- 국가 코드 (ISO 3자리, 중복 방지용 별칭)
                    ci.country_name_ko AS ci_country_name_ko, -- 국가명 (한글)
                    ci.country_name_en AS ci_country_name_en, -- 국가명 (영문)
                    ci.capital,                   -- 수도
                    ci.population,                -- 인구 수
                    ci.population_desc,           -- 인구 설명
                    ci.area_km2,                  -- 면적 (단위: km²)
                    ci.area_desc,                 -- 면적 설명
                    ci.language,                  -- 언어
                    ci.religion,                  -- 종교
                    ci.ethnicity,                 -- 민족
                    ci.climate,                   -- 기후
                    ci.independence              -- 건국일자 (독립일 등)
                FROM
                    country_advisory ca
                JOIN
                    country_info ci 
                ON ca.iso3 = ci.iso3
                WHERE
                    ca.iso3 = ?
                ;
                """;
        return jdbcTemplate.queryForObject(sql, new CountryRowMapper(), iso3);
    }
}
