package com.example.springbootdemo.dao;

import com.example.springbootdemo.models.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserDAO extends JpaRepository<ClientUser,Integer> {

    ClientUser findByEmail (String email);
}
