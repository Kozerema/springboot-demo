package com.example.springbootdemo.models;

import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = Views.Level1.class)
    private int id;

    @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String name;

    @JsonView(value = {Views.Level1.class, Views.Level2.class, Views.Level3.class})
    private String producer;

    @Max(value = 1100,message = "too much")
    @Min(value = 0,message = "can`t be lower than 0")
    @JsonView(value = {Views.Level1.class, Views.Level2.class})
    private int power;

    public Car(String name, String producer, int power) {
        this.name = name;
        this.producer = producer;
        this.power = power;
    }
}
