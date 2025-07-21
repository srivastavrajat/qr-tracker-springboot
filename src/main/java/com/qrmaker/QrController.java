package com.qrmaker;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
class QrService {

    @Autowired
    private QRDataRepository qrDataRepository;

    @Autowired
    private ScanLogRepository scanLogRepository;

    // Save original URL and return generated UUID
    public UUID save(String url) {
        QRData data = new QRData();
        data.setId(UUID.randomUUID());
        data.setOriginalUrl(url);
        qrDataRepository.save(data);
        return data.getId();
    }

    // Generate QR Code image as byte array
    public byte[] generateQR(String url) throws Exception {
        BitMatrix matrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);
        return outputStream.toByteArray();
    }

    // Get original URL from database by ID
    public String getRedirectUrl(UUID id) {
        return qrDataRepository.findById(id)
                .map(QRData::getOriginalUrl)
                .orElse("https://example.com/not-found");
    }

    // Log the scan into the database
    public void logScan(UUID id, String ip, String userAgent) {
        ScanLog log = new ScanLog(id, ip, userAgent);  // constructor with UUID, IP, UA, date
        scanLogRepository.save(log);
    }

    // Get all scan logs from DB
    public List<ScanLog> getAllLogs() {
        return scanLogRepository.findAll();
    }
}
