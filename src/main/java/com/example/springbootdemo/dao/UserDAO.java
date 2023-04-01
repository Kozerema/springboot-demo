package com.example.springbootdemo.dao;

import com.example.springbootdemo.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {

   @Query("select u from User u where u.name>:name ")
    List<User> getUserByName(@Param("name") String name);

   List<User> findByName(String name);

    void deleteById(int id);

    @Modifying
    @Transactional
    void deleteAllByName(@Param("name") String value);

}
