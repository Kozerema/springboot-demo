package com.example.springbootdemo.controllers;

import ch.qos.logback.core.net.server.Client;
import com.example.springbootdemo.dao.UserDAO;
import com.example.springbootdemo.models.User;
import com.example.springbootdemo.models.UserDTO;
import com.example.springbootdemo.queryFilters.UserSpecifications;
import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value="/users")
public class MainController {

    private UserDAO userDAO;
    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid User user){

        userDAO.save(user);

    }

    @GetMapping("")
    @JsonView(value = Views.Client.class)
    public ResponseEntity< List<User>> getUsers(){

        List<User> all = userDAO.findAll(UserSpecifications.byId(9)
                .and(UserSpecifications.byAge(15))
                .and(UserSpecifications.byName("abrikos"))
        );

        return new ResponseEntity<>(all, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id){
        User user = userDAO.findById(id).get();
        UserDTO userDTO= new UserDTO(user);
        return userDTO ;
    }
    @DeleteMapping("/{id}")
    public List<User> deleteUsers(@PathVariable("id") int id){
        userDAO.deleteById(id);
        return userDAO.findAll();
    }
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable("id") int id,@RequestBody User user){

//        User u = userDAO.findById(id).orElse(new User()); якщо немає об'єкта з таким id, створює його
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

    @GetMapping("/name/{nameValue}")
    @JsonView(value = Views.Admin.class)
    public List<User> usersByName(@PathVariable ("nameValue") String nameValue){

//        List<User> userByName = userDAO.getUserByName(nameValue);
//        return userByName;

        return userDAO.findByName(nameValue);

    }

    @DeleteMapping("/all/{name}")
    public void deleteAllByName(@PathVariable String name) {
        userDAO.deleteAllByName(name);
    }

}
