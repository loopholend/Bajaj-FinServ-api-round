package com.bajaj.finserv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getHome() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Bajaj Finserv REST API is active and running!");
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("health_check", "/health (GET)");
        endpoints.put("bfhl_operation", "/bfhl (GET/POST)");
        response.put("endpoints", endpoints);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status", "healthy");
        return ResponseEntity.ok(statusMap);
    }
}
