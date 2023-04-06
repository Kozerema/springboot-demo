package com.example.springbootdemo.models;

import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

import javax.swing.text.View;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = Views.Admin.class)
    private int id;
//    @Pattern(regexp = "[a-z]")
//    @Email
    @NotBlank(message = "name can`t be empty") // = @NotEmpty + NotEmpty
    @Size(min = 2,message = "name is too short")
    @Size(max = 10,message = "name is too long")
    @JsonView(value= {Views.Client.class,Views.Admin.class})
    private String name;

    @Min(value = 0, message = "age can`t be lower then 0")
    @Max(value = 123, message = "age can`t be bigger then 123")
    @JsonView(value = {Views.Client.class,Views.Admin.class})
    private int age;

    private String avatar;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
