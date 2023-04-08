package com.example.springbootdemo.services;

import com.example.springbootdemo.dao.ClientUserDAO;
import com.example.springbootdemo.models.ClientUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientUserService implements UserDetailsService {

   private ClientUserDAO clientUserDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);

        ClientUser byEmail = clientUserDAO.findByEmail(username);
        System.out.println(byEmail);
        return byEmail;
    }

}
