package com.example.demoSpringSecurity1.service;

import com.example.demoSpringSecurity1.Entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    com.example.demoSpringSecurity1.Repository.userRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "user added successfully";
    }

    public String deleteUser(Integer id) {
        userRepository.deleteById(id);
        return "user deleted successfully";
    }
}
