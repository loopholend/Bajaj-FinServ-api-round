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
        
        // Setting student details from application.properties
        response.setUserId(userId);
        response.setEmail(email);
        response.setRollNumber(regNo);

        // Validation for empty or null list
        if (data == null || data.isEmpty()) {
            response.setSuccess(false);
            response.setOddNumbers(new ArrayList<>());
            response.setEvenNumbers(new ArrayList<>());
            response.setAlphabets(new ArrayList<>());
            response.setSpecialCharacters(new ArrayList<>());
            response.setSum("0");
            response.setConcatString("");
            return response;
        }

        response.setSuccess(true);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();
        int sumOfNumbers = 0;

        // Loop through the input array and categorize each item
        for (String item : data) {
            if (item == null || item.trim().isEmpty()) {
                continue;
            }

            // Check if the item is a number
            if (item.matches("\\d+")) {
                int number = Integer.parseInt(item);
                sumOfNumbers += number;
                
                if (number % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } 
            // Check if the item is alphabetic (single or multiple letters)
            else if (item.matches("[a-zA-Z]+")) {
                alphabets.add(item.toUpperCase());
            } 
            // If it is a special character or mixed
            else {
                specialChars.add(item);
            }
        }

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialChars);
        response.setSum(String.valueOf(sumOfNumbers));

        // Logic for reversing and alternating caps for alphabetic characters
        String concatenatedString = getReversedAlternatingCaps(data);
        response.setConcatString(concatenatedString);

        return response;
    }

    // Helper method to extract, reverse and alternate caps of alphabet characters
    private String getReversedAlternatingCaps(List<String> data) {
        StringBuilder lettersOnly = new StringBuilder();

        // Extract all alphabetical characters
        for (String item : data) {
            if (item != null && item.matches("[a-zA-Z]+")) {
                lettersOnly.append(item);
            }
        }

        if (lettersOnly.length() == 0) {
            return "";
        }

        // Reverse the letters
        String reversed = lettersOnly.reverse().toString();

        // Convert to alternating case starting with uppercase
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(ch));
            } else {
                result.append(Character.toLowerCase(ch));
            }
        }
        
        return result.toString();
    }
}
