package com.example.demo.service;

import org.springframework.stereotype.Service;


@Service
public class EmailService {
    public void send(String to, String content) {
        System.out.println("📧 Email enviado para: " + to);
        System.out.println("📨 Conteúdo: " + content);
        // Aqui você colocaria o código real de envio (JavaMailSender etc.)
    }
}
