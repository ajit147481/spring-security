package com.example.demoSpringSecurity1.Controller;

import com.example.demoSpringSecurity1.JWT.AuthRequest;
import com.example.demoSpringSecurity1.Entity.UserInfo;
import com.example.demoSpringSecurity1.JWT.JwtService;
import com.example.demoSpringSecurity1.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    Service service;
    @GetMapping("/add")
    public String addUser(){
        return "this is add user";
    }
    @GetMapping("/insert")
    public String insertUser(){
        return "this is insert user";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasRole('ROLE_NORMAL')")
    public String deleteUser(){
        return "this is delete user";
    }

    @GetMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUser(){
        return "this is update user";
    }
    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }
    @PostMapping("/deleteUser")
    public String addUser(@RequestParam("id") Integer id){
        return service.deleteUser(id);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody()AuthRequest authRequest){
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getEmail());
        }else {
            throw new RuntimeException("user is invalid");
        }
    }
}
