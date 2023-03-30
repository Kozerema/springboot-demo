package com.example.springbootdemo.controllers;

import com.example.springbootdemo.models.User;
import org.apache.catalina.Manager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {

    private List<User> users = new ArrayList<>();

    public MainController() {
        users.add(new User(1, "vika"));
        users.add(new User(2, "vik"));
        users.add(new User(3, "vi"));
        users.add(new User(4, "v"));
    }

    @GetMapping("/")
    public String homePage() {
        return "hello";
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return users;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        List<User> collect = users
                .stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());

        return collect.get(0);
    }

    @PostMapping("/users")
    public List<User> saveUser(@RequestBody User user) {
        System.out.println("hello");
        users.add(user);
        return users;
    }
    //put замінити об'єкт на інший
    //patch оновити
    //delete
}
