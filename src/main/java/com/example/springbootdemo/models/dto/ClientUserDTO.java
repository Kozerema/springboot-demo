package com.example.springbootdemo.models.dto;

import com.example.springbootdemo.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClientUserDTO {
    private String username;
    private String password;
//    private Role role;
}
