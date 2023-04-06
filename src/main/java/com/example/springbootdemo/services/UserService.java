package com.example.springbootdemo.services;


import com.example.springbootdemo.dao.UserDAO;
import com.example.springbootdemo.models.User;
import com.example.springbootdemo.models.UserDTO;
import com.example.springbootdemo.queryFilters.UserSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private MailService mailService;

    public void save(User user) {
        if (user == null) {
            throw new RuntimeException();
        }
        userDAO.save(user);
//        mailService.sendEmail(user);

    }

    public ResponseEntity<List<User>> findAllWithSpecifications(Specification<User> criteria) {

        List<User> all = userDAO.findAll(criteria);

        return new ResponseEntity<>(all, HttpStatus.OK);

    }

    public List<User> findAll() {

        return userDAO.findAll();

    }

    public UserDTO getUser(int id) {

        UserDTO userDTO = null;
        if (id > 0) {
            userDTO = new UserDTO(userDAO.findById(id).get());
        }
        return userDTO;
    }

    public List<User> deleteUserById(int id) {
        if (id > 0) userDAO.deleteById(id);
        return userDAO.findAll();
    }

    public User updateUser(int id, User user) {
        User user1 = null;
        if (id > 0) {
            user1 = userDAO.findById(id).get();
            user1.setName(user.getName());
            userDAO.save(user1);
        }

        return user1;
    }

    public List<User> userByName(String nameValue) {
        return userDAO.findByName(nameValue);
    }

    public void deleteAllByName(String name) {
        userDAO.deleteAllByName(name);
    }


}
