package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/getAllByMultiThread")
    public ResponseEntity<?> getAllByMultiThread() {
        return ResponseEntity.ok(userService.findAll());
    }


    @PostMapping("/save")
    public void save() {
        userService.save();
    }


}
