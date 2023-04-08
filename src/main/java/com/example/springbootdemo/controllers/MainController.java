package com.example.springbootdemo.controllers;

import com.example.springbootdemo.dao.ClientUserDAO;
import com.example.springbootdemo.models.ClientUser;
import com.example.springbootdemo.models.dto.ClientUserDTO;
import com.example.springbootdemo.services.ClientUserService;
import com.sun.xml.bind.v2.TODO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor

public class MainController {

    private ClientUserDAO clientUserDAO;
    private PasswordEncoder passwordEncoder;
    private ClientUserService clientUserService;

    @PostMapping("clients/save")
    public void saveClient(@RequestBody ClientUserDTO clientUserDTO) {

        //don`t working
//
//        clientUserDAO.save(ClientUser.builder()
//                .email(clientUserDTO.getUsername())
//                .password(passwordEncoder.encode(clientUserDTO.getPassword()))
//                .build());


        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(clientUserDTO.getUsername());
        clientUser.setPassword(passwordEncoder.encode(clientUserDTO.getPassword()));


        clientUserDAO.save(clientUser);

    }
    @PostMapping("clients/login")
    public ResponseEntity<String >login(@RequestBody ClientUserDTO clientUserDTO){

        return clientUserService.login(clientUserDTO);

    }

    // make a View
    @GetMapping("clients/all")
    public String getAllClientsWihtoutSensetive(){

        return clientUserDAO
                .findAll()
                .stream()
                .map(clientUser -> clientUser.getEmail())
                .collect(Collectors.toList()).toString();
    }

    @GetMapping("admin/all")
    public List<ClientUser> getAll(){

        return clientUserDAO.findAll();
    }
}