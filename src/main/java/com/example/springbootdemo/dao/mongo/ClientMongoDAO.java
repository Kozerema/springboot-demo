package com.example.springbootdemo.dao.mongo;

import com.example.springbootdemo.models.mongoModels.Client;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoDAO extends MongoRepository<Client, ObjectId> {
}