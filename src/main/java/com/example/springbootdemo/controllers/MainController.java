package com.example.springbootdemo.controllers;

import com.example.springbootdemo.dao.CarDAO;
import com.example.springbootdemo.models.Car;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class MainController {
    private CarDAO carDAO;

    @GetMapping("")
    public List<Car> getCars() {
        Sort by = Sort.by(Sort.Order.desc("id"));
        return carDAO.findAll(by);
    }

    @GetMapping("/{id}")
    public Car getOneCar(@PathVariable int id) {
        Car car = carDAO.findById(id).get();
        return car;
    }

    @PostMapping("")
    public void save(@RequestBody Car car) {
        carDAO.save(car);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    @GetMapping("/power/{power}")
    public List<Car> findCarByPower(@PathVariable int power) {
        return carDAO.findCarByPower(power);
    }

    @GetMapping("producer/{producer}")
    public List<Car> findCarByProducer(@PathVariable String producer){
//        return carDAO.findCarByProducer(producer);

        return carDAO.getCarByProducer(producer);

    }

}
