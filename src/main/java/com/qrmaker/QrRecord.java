package com.qrmaker;

import java.util.UUID;
public class QrRecord {
    private UUID id;
    private String originalUrl;

    public QrRecord(UUID id, String originalUrl) {
        this.id = id;
        this.originalUrl = originalUrl;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
// Getters & setters
}