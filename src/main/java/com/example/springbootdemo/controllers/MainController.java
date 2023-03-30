package com.example.springbootdemo.controllers;

import com.example.springbootdemo.dao.UserDAO;
import com.example.springbootdemo.models.User;
import lombok.AllArgsConstructor;
import org.apache.catalina.Manager;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    @PostMapping("/users")
    public void save(@RequestBody User user){

        userDAO.save(user);

    }

    @GetMapping("/users")
    public List<User> getUsers(){

        List<User> all = userDAO.findAll();
        return all;
    }
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id){
        User user = userDAO.findById(id).get();
        return user;
    }
    @DeleteMapping("users/{id}")
    public List<User> deleteUsers(@PathVariable("id") int id){
        userDAO.deleteById(id);
        return userDAO.findAll();
    }
    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable("id") int id,@RequestBody User user){

//        User u = userDAO.findById(id).orElse(new User()); якщо немає об'єкта з таким id, створює його
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

}
