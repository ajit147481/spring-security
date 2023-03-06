package com.example.demoSpringSecurity1.Config;

import com.example.demoSpringSecurity1.service.userDetailServiceUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class config {
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails user1= User.withUsername("ajit")
//                .password(encoder.encode("ajit"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2= User.withUsername("asmita")
//                .password(encoder.encode("asmita"))
//                .roles("NORMAL")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
        return new userDetailServiceUser();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/addUser","/user/deleteUser","/user/**")
                .permitAll()
                .and()
                .formLogin()
                .and()
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
