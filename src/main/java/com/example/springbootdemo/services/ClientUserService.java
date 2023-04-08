package com.example.springbootdemo.services;

import com.example.springbootdemo.dao.ClientUserDAO;
import com.example.springbootdemo.models.ClientUser;
import com.example.springbootdemo.models.dto.ClientUserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service

public class ClientUserService implements UserDetailsService {

   private ClientUserDAO clientUserDAO;
   private AuthenticationManager authenticationManager;

   @Autowired
    public ClientUserService(ClientUserDAO clientUserDAO, @Lazy AuthenticationManager authenticationManager) {
        this.clientUserDAO = clientUserDAO;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);

        ClientUser byEmail = clientUserDAO.findByEmail(username);
        System.out.println(byEmail);
        return byEmail;
    }

    public ResponseEntity<String> login(ClientUserDTO clientUserDTO) {
        System.out.println(clientUserDTO);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                clientUserDTO.getUsername(), clientUserDTO.getPassword()
        );
        System.out.println(usernamePasswordAuthenticationToken);

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(authenticate!=null){
            String jwtToken = Jwts.builder()
                    .setSubject(authenticate.getName()) ///username-kokos
                    .signWith(SignatureAlgorithm.HS512, "okten".getBytes(StandardCharsets.UTF_8))
                    .compact();
            System.out.println(jwtToken);


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization","Bearer "+jwtToken);
            return new ResponseEntity<>("you are logged in",httpHeaders, HttpStatus.OK);

        }
        return new ResponseEntity<>("bad credentials", HttpStatus.FORBIDDEN);
    }
}
