// UrlRequest.java
package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class UrlRequest {
    @NotBlank(message = "URL cannot be empty")
    @URL(message = "Please provide a valid URL")
    private String originalUrl;

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }
}

