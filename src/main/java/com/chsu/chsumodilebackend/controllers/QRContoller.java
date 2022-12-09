package com.chsu.chsumodilebackend.controllers;

import com.chsu.chsumodilebackend.services.QrCodeGenerator;
import com.chsu.chsumodilebackend.services.UserService;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.DataBuffer;

@Controller
@RequestMapping("/qr")
@CrossOrigin
public class QRContoller {

    @Autowired
    QrCodeGenerator qrCodeGenerator;

    @Autowired
    UserService userService;

    @GetMapping(value = "/getQr")
    public ResponseEntity<byte []> getQrCode (@RequestParam String login, @RequestParam String lesson, @RequestParam String time) {
        return ResponseEntity.ok(qrCodeGenerator.saveQr(userService.findByLogin(login), lesson, time));
    }
}
