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

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        int sum = 0;

        // separate each element into its category
        for (String item : data) {
            if (item == null || item.isEmpty()) continue;

            if (item.matches("\\d+")) {
                // its a number
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (item.matches("[a-zA-Z]+")) {
                // its alphabetical
                alphabets.add(item.toUpperCase());
            } else {
                // special character or mixed
                specialCharacters.add(item);
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));

        // build concat_string
        // 1. collect all individual alpha chars from alphabetical elements
        // 2. reverse the order
        // 3. alternating caps (upper, lower, upper, lower...)
        response.setConcatString(buildConcatString(data));

        return response;
    }

    private String buildConcatString(List<String> data) {
        List<Character> allChars = new ArrayList<>();

        for (String item : data) {
            if (item == null) continue;
            if (item.matches("[a-zA-Z]+")) {
                for (char c : item.toCharArray()) {
                    allChars.add(c);
                }
            }
        }

        if (allChars.isEmpty()) return "";

        // reverse
        Collections.reverse(allChars);

        // alternating caps starting with uppercase
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allChars.size(); i++) {
            char c = allChars.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }
}
