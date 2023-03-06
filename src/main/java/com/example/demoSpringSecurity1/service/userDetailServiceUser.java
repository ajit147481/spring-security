package com.example.demoSpringSecurity1.service;

import com.example.demoSpringSecurity1.Config.userInfoUserDetails;
import com.example.demoSpringSecurity1.Entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class userDetailServiceUser implements UserDetailsService {
    @Autowired
    com.example.demoSpringSecurity1.Repository.userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserInfo> user=userRepository.findByName(username);
       return user.map(userInfoUserDetails::new)
               .orElseThrow(()-> new UsernameNotFoundException("user not found "+username));
    }
}
