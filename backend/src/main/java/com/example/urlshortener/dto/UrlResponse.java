// UrlResponse.java
package com.example.urlshortener.dto;
import java.time.LocalDateTime;

public class UrlResponse {
    private String originalUrl;
    private String shortUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDateTime createdAt;

    public UrlResponse(String originalUrl, String shortUrl, String shortCode, Long clickCount, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
    }
    // Getters
    public String getOriginalUrl() { return originalUrl; }
    public String getShortUrl() { return shortUrl; }
    public String getShortCode() { return shortCode; }
    public Long getClickCount() { return clickCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
