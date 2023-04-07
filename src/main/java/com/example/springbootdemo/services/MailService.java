package com.example.springbootdemo.services;

import com.example.springbootdemo.models.Car;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


public interface MailService {


    void send(Car car) throws MessagingException;

}
