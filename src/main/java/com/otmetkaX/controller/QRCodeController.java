package com.otmetkaX.controller;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/lukoil")
public class QRCodeController {

    private final String teamName = "otmetkaX";
    private final QRCodeService service;

    @Autowired
    public QRCodeController(QRCodeService service) {
        this.service = service;
    }

    @GetMapping("QRCode")
    public CompletableFuture<ResponseEntity<byte[]>> getQRCode() throws CustomException {
        return CompletableFuture.supplyAsync(() -> {
            BufferedImage qrImage = service.generateQRCode(teamName);
            try {
                byte[] imageBytes = service.imageToByteArrayPNG(qrImage);
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
            } catch ( CustomException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        });
    }

}
