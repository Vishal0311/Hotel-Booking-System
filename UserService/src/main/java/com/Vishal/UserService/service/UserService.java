package com.Vishal.UserService.service;

import com.Vishal.UserService.model.UserRequest;
import com.Vishal.UserService.model.UserResponse;

import java.util.List;

public interface UserService {
    long createUser(UserRequest userRequest);

    List<UserResponse> getAllUser();

    UserResponse getUserById(Long userId);
}
