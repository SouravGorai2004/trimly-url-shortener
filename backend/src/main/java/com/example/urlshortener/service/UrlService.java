package com.example.urlshortener.service;

import com.example.urlshortener.dto.*;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlService {

    private final UrlMappingRepository repository;
    private final Base62Encoder base62Encoder;

    // Pulls the URL from application.properties (or environment variables)
    @Value("${app.base-url}")
    private String baseUrl;

    public UrlService(UrlMappingRepository repository, Base62Encoder base62Encoder) {
        this.repository = repository;
        this.base62Encoder = base62Encoder;
    }

    @Transactional
    public UrlResponse createShortUrl(UrlRequest request) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(request.getOriginalUrl());

        mapping = repository.save(mapping);

        String shortCode = base62Encoder.encode(mapping.getId());
        mapping.setShortCode(shortCode);
        repository.save(mapping);

        return mapToResponse(mapping);
    }

    @Transactional
    public String getOriginalUrlAndIncrementClicks(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        mapping.setClickCount(mapping.getClickCount() + 1);
        repository.save(mapping);

        return mapping.getOriginalUrl();
    }

    public UrlResponse getUrlStatistics(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
        return mapToResponse(mapping);
    }

    private UrlResponse mapToResponse(UrlMapping mapping) {
        return new UrlResponse(
                mapping.getOriginalUrl(),
                baseUrl + mapping.getShortCode(),
                mapping.getShortCode(),
                mapping.getClickCount(),
                mapping.getCreatedAt()
        );
    }
}