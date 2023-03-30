package com.example.springbootdemo.dao;

import com.example.springbootdemo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDAO extends JpaRepository<Car,Integer> {

    List<Car> findCarByPower(int power);

//    List<Car> findCarByProducer(String producer);

    @Query("select c from Car c where c.producer=:producer")
    List<Car> getCarByProducer(@Param("producer") String producer);


}
