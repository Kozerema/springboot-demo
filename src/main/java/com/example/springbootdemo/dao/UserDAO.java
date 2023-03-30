package com.example.springbootdemo.dao;

import com.example.springbootdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {

}
