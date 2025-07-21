package com.qrmaker;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RestController
public class QrController {

    @Autowired
    private QrService qrService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestParam String link) throws Exception {
        UUID id = qrService.save(link);
        String baseUrl = "https://qr-tracker-springboot.onrender.com";
        byte[] qr = qrService.generateQR(baseUrl + "/track/" + id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qr);
    }

    @GetMapping("/track/{id}")
    public RedirectView track(@PathVariable UUID id, HttpServletRequest req) {
        qrService.logScan(id, req.getRemoteAddr(), req.getHeader("User-Agent"));
        return new RedirectView(qrService.getRedirectUrl(id));
    }

    @GetMapping("/logs")
    public Object getLogs() {
        return qrService.getAllLogs();
    }
}
