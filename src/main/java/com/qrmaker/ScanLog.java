package com.qrmaker;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScanLog {
    private UUID qrId;
    private String ip;
    private String userAgent;
    private LocalDateTime timestamp;

    public ScanLog(UUID qrId, String ip, String userAgent) {
        this.qrId = qrId;
        this.ip = ip;
        this.userAgent = userAgent;
        this.timestamp = LocalDateTime.now();
    }

    // Getters & setters

    public UUID getQrId() {
        return qrId;
    }

    public void setQrId(UUID qrId) {
        this.qrId = qrId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}