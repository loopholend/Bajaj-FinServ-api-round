package com.bajaj.finserv.controller;

import com.bajaj.finserv.dto.BfhlRequest;
import com.bajaj.finserv.dto.BfhlResponse;
import com.bajaj.finserv.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<BfhlResponse> handlePost(@RequestBody BfhlRequest request) {
        try {
            BfhlResponse response = bfhlService.processData(request.getData());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
            e.printStackTrace();

            BfhlResponse errorResp = new BfhlResponse();
            errorResp.setSuccess(false);
            return ResponseEntity.ok(errorResp);
        }
    }

    @GetMapping
    public ResponseEntity<String> handleGet() {
        return ResponseEntity.ok("{\"operation_code\": 1}");
    }
}
