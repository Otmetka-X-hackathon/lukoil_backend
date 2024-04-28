package com.otmetkaX.service;

import com.otmetkaX.exception.CustomException;

import java.awt.image.BufferedImage;

public interface QRCodeService {
    BufferedImage generateQRCode(String teamName);
    byte[] imageToByteArrayPNG(BufferedImage image) throws CustomException;
}
