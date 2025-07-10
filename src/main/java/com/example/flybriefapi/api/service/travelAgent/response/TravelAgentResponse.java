package com.example.flybriefapi.api.service.travelAgent.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TravelAgentResponse {
    private String message;
    private List<String> iso3;

    @Builder
    public TravelAgentResponse(String message, List<String> iso3) {
        this.message = message;
        this.iso3 = iso3;
    }
}
