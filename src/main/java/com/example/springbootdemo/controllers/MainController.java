package com.example.springbootdemo.controllers;

import com.example.springbootdemo.dao.CarDAO;
import com.example.springbootdemo.models.Car;
import com.example.springbootdemo.services.CarService;
import com.example.springbootdemo.services.MailService;
import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.mail.MessagingException;
import jakarta.persistence.JoinColumn;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class MainController {
    private CarService carService;


    @GetMapping("")
    @JsonView(value = Views.Level3.class)
    public ResponseEntity< List<Car>> getCars() {
       return carService.getCars();
    }

    @GetMapping("/{id}")
    @JsonView(value = Views.Level1.class)
    public ResponseEntity<Car> getOneCar(@PathVariable int id) {

        return carService.getOneCar(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void saveCar(@RequestBody Car car) throws MessagingException {
        carService.save(car);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable int id) {
        carService.deleteById(id);
    }

    @GetMapping("/power/{power}")
    @JsonView(value = Views.Level2.class)
    public ResponseEntity<List<Car>> findCarByPower(@PathVariable int power) {

        return carService.findCarByPower(power);
    }

    @GetMapping("producer/{producer}")
    @JsonView(value = Views.Level2.class)
    public ResponseEntity<List<Car>> findCarByProducer(@PathVariable String producer){
//        return carDAO.findCarByProducer(producer);

     return carService.findCarByProducer(producer);

    }

}
