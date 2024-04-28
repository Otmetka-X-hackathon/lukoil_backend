package com.otmetkaX.service.impl;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.service.QRCodeService;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public BufferedImage generateQRCode(String teamName) {
        try {
            // Генерация QR-кода
            BufferedImage qrImage = generateQRCodeImage(teamName);

            return generateQRCodeImage(teamName);
//            return convertImageToBase64(qrImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage generateQRCodeImage(String text) throws WriterException {
        int size = 300; // Размер QR-кода
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size);
        BufferedImage qrImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return qrImage;
    }

    @Override
    public byte[] imageToByteArrayPNG(BufferedImage image) throws CustomException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            throw new CustomException("IMAGE_INVALID", 400);
        }
        return baos.toByteArray();
    }


}
