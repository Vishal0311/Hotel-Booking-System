package com.Vishal.UserService.service;

import com.Vishal.UserService.entity.User;
import com.Vishal.UserService.model.UserRequest;
import com.Vishal.UserService.model.UserResponse;
import com.Vishal.UserService.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;


@Service
@Log4j2
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public long createUser(UserRequest userRequest) {
        log.info("Adding user to db");
        User user = User.builder()
                .userEmail(userRequest.getUserEmail())
                .userPassword(userRequest.getUserPassword())
                .userName(userRequest.getUserName())
                .userPhoneNumber(userRequest.getUserPhoneNumber())
                .build();

        userRepository.save(user);

        log.info("User added");

        return user.getUserId();
    }

    @Override
    public List<UserResponse> getAllUser() {

        log.info("Getting all user");
        List<User> userList= userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user:userList){
            UserResponse userResponse = new UserResponse();
            userResponse.setUserEmail(user.getUserEmail());
            userResponse.setUserName(user.getUserName());
            userResponse.setUserPhoneNumber(user.getUserPhoneNumber());
            userResponseList.add(userResponse);
        }


        return userResponseList;
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with id: "+userId));

        UserResponse userResponse = new UserResponse();

        copyProperties(user,userResponse);

        return userResponse;
    }
}
