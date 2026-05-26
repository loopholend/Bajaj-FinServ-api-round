package com.bajaj.finserv.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> statusMap = new HashMap<>();
        statusMap.put("status", "healthy");
        return ResponseEntity.ok(statusMap);
    }
}
