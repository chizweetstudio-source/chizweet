package com.chizweet.tokoonline.controller;

import com.chizweet.tokoonline.model.ContactRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @PostMapping
    public ResponseEntity<Map<String, String>> contact(@RequestBody ContactRequest request) {
        System.out.println("=== Pesan Masuk dari: " + request.getName() + " ===");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Produk Diminati: " + request.getProduct());
        System.out.println("Pesan: " + request.getMessage());
        System.out.println("==========================================");
        return ResponseEntity.ok(Map.of("status", "success", "message", "Pesan Anda sudah kami terima! Kami akan segera menghubungi Anda."));
    }
}
