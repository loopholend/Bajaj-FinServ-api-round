package com.bajaj.finserv.controller;

import com.bajaj.finserv.dto.BfhlRequest;
import com.bajaj.finserv.dto.BfhlResponse;
import com.bajaj.finserv.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    // POST Request handler
    @PostMapping
    public ResponseEntity<BfhlResponse> handlePost(@RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request.getData());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error in handlePost: " + e.getMessage());
            e.printStackTrace();
            
            BfhlResponse errorResponse = new BfhlResponse();
            errorResponse.setSuccess(false);
            return ResponseEntity.ok(errorResponse);
        }
    }

    // GET Request handler
    @GetMapping
    public ResponseEntity<Map<String, Integer>> handleGet() {
        Map<String, Integer> result = new HashMap<>();
        result.put("operation_code", 1);
        return ResponseEntity.ok(result);
    }
}
