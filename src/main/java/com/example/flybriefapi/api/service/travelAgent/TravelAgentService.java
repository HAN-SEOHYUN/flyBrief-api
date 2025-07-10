package com.example.flybriefapi.api.service.travelAgent;

import com.example.flybriefapi.api.service.travelAgent.request.TravelAgentClient;
import com.example.flybriefapi.api.service.travelAgent.response.TravelAgentResponse;
import org.springframework.stereotype.Service;

@Service
public class TravelAgentService {
    private final TravelAgentClient travelAgentClient;

    public TravelAgentService(TravelAgentClient travelAgentClient) {
        this.travelAgentClient = travelAgentClient;
    }

    public TravelAgentResponse fetchTravelRecommendations(String travelTheme) {
        return travelAgentClient.getRecommendations(travelTheme);
    }
}
