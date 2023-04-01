package com.example.springbootdemo.queryFilters;

import com.example.springbootdemo.models.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<User> byAge(int age) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get("age"), age);
    }

    public static Specification<User> byId(int id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }
}
