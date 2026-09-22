// Base62Encoder.java
package com.example.urlshortener.service;
import org.springframework.stereotype.Component;

@Component
public class Base62Encoder {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALLOWED_CHARACTERS.length();

    public String encode(long input) {
        if (input == 0) return String.valueOf(ALLOWED_CHARACTERS.charAt(0));
        StringBuilder encodedString = new StringBuilder();
        while (input > 0) {
            encodedString.append(ALLOWED_CHARACTERS.charAt((int) (input % BASE)));
            input = input / BASE;
        }
        return encodedString.reverse().toString();
    }
}