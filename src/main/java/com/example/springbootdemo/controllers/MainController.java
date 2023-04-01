package com.example.springbootdemo.controllers;

import com.example.springbootdemo.dao.CarDAO;
import com.example.springbootdemo.models.Car;
import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
    private CarDAO carDAO;

    @GetMapping("")
    @JsonView(value = Views.Level3.class)
    public ResponseEntity< List<Car>> getCars() {
        Sort by = Sort.by(Sort.Order.desc("id"));

        return new ResponseEntity<>( carDAO.findAll(by), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(value = Views.Level1.class)
    public ResponseEntity<Car> getOneCar(@PathVariable int id) {

        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void save( @RequestBody @Valid  Car car) {
        carDAO.save(car);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    @GetMapping("/power/{power}")
    @JsonView(value = Views.Level2.class)
    public ResponseEntity<List<Car>> findCarByPower(@PathVariable int power) {

        return new ResponseEntity<>(carDAO.findCarByPower(power),HttpStatus.OK);
    }

    @GetMapping("producer/{producer}")
    @JsonView(value = Views.Level2.class)
    public ResponseEntity<List<Car>> findCarByProducer(@PathVariable String producer){
//        return carDAO.findCarByProducer(producer);

        return new ResponseEntity<>(carDAO.getCarByProducer(producer),HttpStatus.OK);

    }

}
