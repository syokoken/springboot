package com.example.demo.repository;

import com.example.demo.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    int save(User user);
    int update(User user);
    int delete(Long id);
    User findById(Long id);
}
