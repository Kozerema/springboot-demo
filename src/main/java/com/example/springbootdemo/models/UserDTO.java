package com.example.springbootdemo.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String name;
    private int age;
    private String avatar;

    public UserDTO (User user){
        this.name=user.getName();
        this.age=user.getAge();
        this.avatar=user.getAvatar();
    }
}
