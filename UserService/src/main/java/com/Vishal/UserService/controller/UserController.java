package com.Vishal.UserService.controller;


import com.Vishal.UserService.model.UserRequest;
import com.Vishal.UserService.model.UserResponse;
import com.Vishal.UserService.repository.UserRepository;
import com.Vishal.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserRequest userRequest){
        long userId = userService.createUser(userRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    @GetMapping("/allUser")
    public List<UserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);
    }
}
