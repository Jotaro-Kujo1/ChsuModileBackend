package com.chsu.chsumodilebackend.services;

import com.chsu.chsumodilebackend.dao.QrRepository;
import com.chsu.chsumodilebackend.models.Qr;
import com.chsu.chsumodilebackend.models.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import java.util.*;
import java.util.Hashtable;
import java.util.List;

@Service
public class QrCodeGenerator {

    @Autowired
    QrRepository qrRepository;


    public byte [] saveQr(User user, String lesson, String time) {
        Qr myQr = new Qr();
        String qrCodeText = user.getName().concat(lesson).concat(time);
        try {
            myQr.setQr(generateQr(qrCodeText));
            return qrRepository.save(myQr).getQr();
        } catch (Exception ex) {
            return null;
        }
    }

    public byte [] generateQr(String qrCodeText) throws WriterException, IOException {
        String filePath = "JD.png";
        String fileType = "png";
        File qrFile = new File(filePath);
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


        BufferedImage bImage = ImageIO.read(new File("JD.png"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos);
        byte [] data = bos.toByteArray();
        return data;
    }

}
