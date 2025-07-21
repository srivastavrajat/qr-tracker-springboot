package com.qrmaker;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class QrService {
    private final Map<UUID, QrRecord> qrLinks = new HashMap<>();
    private final List<ScanLog> logs = new ArrayList<>();

    public UUID save(String url) {
        UUID id = UUID.randomUUID();
        qrLinks.put(id, new QrRecord(id, url));
        return id;
    }

    public byte[] generateQR(String url) throws Exception {
        BitMatrix matrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", out);
        return out.toByteArray();
    }

    public String getRedirectUrl(UUID id) {
        return qrLinks.getOrDefault(id, new QrRecord(null, "https://google.com")).getOriginalUrl();
    }

    public void logScan(UUID id, String ip, String userAgent) {
        logs.add(new ScanLog(id, ip, userAgent));
    }

    public List<ScanLog> getAllLogs() {
        return logs;
    }
}
