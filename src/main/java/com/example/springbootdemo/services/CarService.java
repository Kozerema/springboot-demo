package com.example.springbootdemo.services;

import com.example.springbootdemo.dao.CarDAO;
import com.example.springbootdemo.models.Car;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private MailService mailService;
    private CarDAO carDAO;

    public CarService(@Qualifier("mailService1") MailService mailService, CarDAO carDAO) {
        this.mailService = mailService;
        this.carDAO = carDAO;
    }

    public void save(Car car) throws MessagingException {
        if (car == null) {
            throw new RuntimeException();
        }
        carDAO.save(car);
        mailService.send(car);
    }

    public ResponseEntity<Car>  getOneCar(int id){
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<List<Car>> getCars(){
        Sort sort = Sort.by(Sort.Order.desc("id"));

        return new ResponseEntity<>( carDAO.findAll(sort), HttpStatus.OK);
    }

    public void deleteById (int id){
        if (id==0){
            throw new RuntimeException();
        }
        carDAO.deleteById(id);
    }

    public ResponseEntity<List<Car>> findCarByPower(int power){
        return new ResponseEntity<>(carDAO.findCarByPower(power),HttpStatus.OK);
    }

    public ResponseEntity<List<Car>> findCarByProducer(String producer){
        return new ResponseEntity<>(carDAO.getCarByProducer(producer),HttpStatus.OK);
    }
}
