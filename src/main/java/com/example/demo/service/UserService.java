package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAll();
    List<User> findAll();

    void save();
}
