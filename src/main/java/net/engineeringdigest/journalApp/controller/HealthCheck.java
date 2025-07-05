package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    // declare end point in Controller and Need to call Services from Service package
    // Controller ---> Service ---> Repository


    @GetMapping("health-check")
    public String healthCheck(){
        return "Health Checking is Okay";
    }
}
