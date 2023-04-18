package com.example.demoSpringSecurity1.Repository;

import com.example.demoSpringSecurity1.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface userRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);

    Optional<UserInfo> findByEmail(String username);
    //public User find
}
