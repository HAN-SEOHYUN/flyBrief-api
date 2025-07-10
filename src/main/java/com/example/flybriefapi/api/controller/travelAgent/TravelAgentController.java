package com.example.flybriefapi.api.controller.travelAgent;

import com.example.flybriefapi.api.ApiResponse;
import com.example.flybriefapi.api.service.travelAgent.TravelAgentService;
import com.example.flybriefapi.api.service.travelAgent.response.TravelAgentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class TravelAgentController {
    private final TravelAgentService travelAgentService;

    public TravelAgentController(TravelAgentService travelAgentService) {
        this.travelAgentService = travelAgentService;
    }

    @GetMapping("/recommendations")
    public ApiResponse<TravelAgentResponse> getTravelRecommendations(@RequestParam String travelTheme) {
        TravelAgentResponse results = travelAgentService.fetchTravelRecommendations(travelTheme);
        return ApiResponse.ok(results);
    }
}
