package com.example.flybriefapi.api.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/api/test")
    public String test() {
        return "test";
    }
}
