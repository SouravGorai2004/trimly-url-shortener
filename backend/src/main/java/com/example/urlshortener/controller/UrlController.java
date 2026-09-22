// UrlController.java (Handles API logic)
package com.example.urlshortener.controller;

import com.example.urlshortener.dto.UrlRequest;
import com.example.urlshortener.dto.UrlResponse;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlResponse shortenUrl(@Valid @RequestBody UrlRequest request) {
        return urlService.createShortUrl(request);
    }

    @GetMapping("/{shortCode}")
    public UrlResponse getStats(@PathVariable String shortCode) {
        return urlService.getUrlStatistics(shortCode);
    }
}