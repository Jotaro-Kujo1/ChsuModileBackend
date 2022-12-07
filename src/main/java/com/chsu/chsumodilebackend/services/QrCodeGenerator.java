package com.chsu.chsumodilebackend.services;

import com.chsu.chsumodilebackend.models.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

@Service
public class QrCodeGenerator {

    public void getQr(User user) {
        String qrCodeText = user.getName().concat(user.getSurname().concat(user.getPatronymic()).concat(user.getJobTitle()));
        String filePath = "JD.png";
        String fileType = "png";
        File qrFile = new File(filePath);
        try {
            generateQr(qrFile, qrCodeText, fileType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generateQr(File qrFile, String qrCodeText, String fileType) throws WriterException, IOException {
        Hashtable hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 250, 250, hintMap);
        BufferedImage image = new BufferedImage(byteMatrix.getWidth(), byteMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0 , byteMatrix.getWidth(), byteMatrix.getHeight());
        graphics2D.setColor(Color.BLACK);

        for (int i = 0; i<byteMatrix.getWidth(); i++){
            for (int j = 0; j<byteMatrix.getWidth(); j++){
                if (byteMatrix.get(i, j)) {
                    graphics2D.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
}
