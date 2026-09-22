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

    // Pulls the URL from application.properties
    @Value("${app.base-url}")
    private String baseUrl;

    public UrlService(UrlMappingRepository repository, Base62Encoder base62Encoder) {
        this.repository = repository;
        this.base62Encoder = base62Encoder;
    }

    // ... keep createShortUrl, getOriginalUrlAndIncrementClicks, and getUrlStatistics exactly the same ...

    private UrlResponse mapToResponse(UrlMapping mapping) {
        // Use the injected baseUrl instead of the hardcoded string
        return new UrlResponse(
                mapping.getOriginalUrl(),
                baseUrl + mapping.getShortCode(),
                mapping.getShortCode(),
                mapping.getClickCount(),
                mapping.getCreatedAt()
        );
    }
}