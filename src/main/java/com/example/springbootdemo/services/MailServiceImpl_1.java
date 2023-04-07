package com.example.springbootdemo.services;

import com.example.springbootdemo.models.Car;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("mailService1")
@AllArgsConstructor
public class MailServiceImpl_1 implements MailService {
    private JavaMailSender javaMailSender;
    @Override
    public void send(Car car) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setTo("kozeremavika@gmail.com");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        helper.setText("car is created"+car.toString()+new Date().getTime(),true);
        javaMailSender.send(mimeMessage);
    }
}
