package com.bajaj.finserv.controller;

import com.bajaj.finserv.dto.BfhlRequest;
import com.bajaj.finserv.dto.BfhlResponse;
import com.bajaj.finserv.service.BfhlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private static final Logger log = LoggerFactory.getLogger(BfhlController.class);
    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponse> handlePost(@RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request.getData());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Failed to process BFHL request", e);
            BfhlResponse errorResp = new BfhlResponse();
            errorResp.setSuccess(false);
            return ResponseEntity.ok(errorResp);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> handleGet() {
        return ResponseEntity.ok(Collections.singletonMap("operation_code", 1));
    }
}
