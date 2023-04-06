package com.example.springbootdemo.controllers;


import com.example.springbootdemo.dao.UserDAO;
import com.example.springbootdemo.dao.mongo.ClientMongoDAO;
import com.example.springbootdemo.models.User;
import com.example.springbootdemo.models.UserDTO;
import com.example.springbootdemo.models.mongoModels.Client;
import com.example.springbootdemo.queryFilters.UserSpecifications;
import com.example.springbootdemo.services.UserService;
import com.example.springbootdemo.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class MainController {

    private UserDAO userDAO;
    private UserService userService;
    private ClientMongoDAO clientMongoDAO;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid User user) {
        userService.save(user);
        clientMongoDAO.save(new Client(user.getName(),user.getAge()));
    }


    @GetMapping("/specifications")
    @JsonView(value = Views.Client.class)
    public ResponseEntity<List<User>> getUsersBySpecifications() {

        return userService.findAllWithSpecifications(UserSpecifications.byId(1)
                .and(UserSpecifications.byAge(32))
                .and(UserSpecifications.byName("abrk")));
    }

    @GetMapping("")
    @JsonView(value = Views.Client.class)
    public List<User> getUsers() {

        return userService.findAll();
    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id) {
        return userService.getUser(id);

    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable("id") int id) {

        return userService.deleteUserById(id);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user) {

//        User u = userDAO.findById(id).orElse(new User()); якщо немає об'єкта з таким id, створює його

        return userService.updateUser(id, user);
    }

    @GetMapping("/name/{nameValue}")
    @JsonView(value = Views.Admin.class)
    public List<User> usersByName(@PathVariable("nameValue") String nameValue) {

//        List<User> userByName = userDAO.getUserByName(nameValue);
//        return userByName;

        return userService.userByName(nameValue);

    }

    @DeleteMapping("/all/{name}")
    public void deleteAllByName(@PathVariable String name) {
        userService.deleteAllByName(name);
    }

//    @PostMapping("/saveWithAvatar")
//    public void saveWithAvatar(@RequestParam String name,
//                               @RequestParam int age,
//                               @RequestParam MultipartFile avatar) throws IOException {
//
//        User user = new User(name, age);
//        String originalFilename = avatar.getOriginalFilename();
//        user.setAvatar("/photo" + originalFilename);
//        String path = System.getProperty("user.home") + File.separator + "img" + File.separator + originalFilename;
//        File file = new File(path);
//        avatar.transferTo(file);
//        userService.save(user);
//
//
//    }
//}

    @PostMapping("/saveWithAvatar")
    public void saveWithAvatar(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam MultipartFile avatar
    ) throws IOException {

        User user = new User(name, age);
        String originalFilename = avatar.getOriginalFilename();
        user.setAvatar("/photo/" + originalFilename);
//        String path = System.getProperty("user.home") + File.separator + "images" + File.separator + originalFilename;
        String path = "D:"+File.separator+"Document" + File.separator + "images" + File.separator + originalFilename;
        File file = new File(path);
        avatar.transferTo(file);
        userService.save(user);
    }

}