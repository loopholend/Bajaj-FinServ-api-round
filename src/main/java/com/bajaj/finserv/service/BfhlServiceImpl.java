package com.bajaj.finserv.service;

import com.bajaj.finserv.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bajaj.userId}")
    private String userId;

    @Value("${bajaj.email}")
    private String email;

    @Value("${bajaj.regNo}")
    private String regNo;

    @Override
    public BfhlResponse processData(List<String> data) {
        BfhlResponse response = new BfhlResponse();
        response.setUserId(userId);
        response.setEmail(email);
        response.setRollNumber(regNo);

        if (data == null || data.isEmpty()) {
            response.setSuccess(false);
            response.setOddNumbers(Collections.emptyList());
            response.setEvenNumbers(Collections.emptyList());
            response.setAlphabets(Collections.emptyList());
            response.setSpecialCharacters(Collections.emptyList());
            response.setSum("0");
            response.setConcatString("");
            return response;
        }

        response.setSuccess(true);

        List<String> odd = new ArrayList<>();
        List<String> even = new ArrayList<>();
        List<String> alpha = new ArrayList<>();
        List<String> special = new ArrayList<>();
        int totalSum = 0;

        // Group inputs into respective categories
        for (String val : data) {
            if (val == null || val.trim().isEmpty()) {
                continue;
            }

            if (val.matches("\\d+")) {
                int num = Integer.parseInt(val);
                totalSum += num;
                if (num % 2 == 0) {
                    even.add(val);
                } else {
                    odd.add(val);
                }
            } else if (val.matches("[a-zA-Z]+")) {
                alpha.add(val.toUpperCase());
            } else {
                special.add(val);
            }
        }

        response.setOddNumbers(odd);
        response.setEvenNumbers(even);
        response.setAlphabets(alpha);
        response.setSpecialCharacters(special);
        response.setSum(String.valueOf(totalSum));

        // Generate the reversed alternating-caps string
        response.setConcatString(generateConcatString(data));

        return response;
    }

    private String generateConcatString(List<String> data) {
        StringBuilder chars = new StringBuilder();

        for (String val : data) {
            if (val != null && val.matches("[a-zA-Z]+")) {
                chars.append(val);
            }
        }

        if (chars.length() == 0) {
            return "";
        }

        // Reverse the accumulated alphabetical characters
        chars.reverse();

        // Convert to alternating case (Upper, lower, Upper, lower...)
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
