package com.example.springbootdemo.models.mongoModels;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
public class Client {

//    @Indexed
    @Id
    private ObjectId id;
    private String name;
    private int age;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
