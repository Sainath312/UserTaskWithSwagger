package com.example.UserTask.service;

import com.example.UserTask.config.UserInfoUserDetails;
import com.example.UserTask.entity.AdminEntity;
import com.example.UserTask.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    AdminRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AdminEntity> userInfoByUserName = repository.findByUserName(username);
        if (userInfoByUserName.isPresent()) {
            return new UserInfoUserDetails(userInfoByUserName.get());
        }
        Optional<AdminEntity> userInfoByEmail = repository.findByEmail(username);
        return userInfoByEmail.map(userInfo -> new UserInfoUserDetails(userInfo, true))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Username or mobile number: " + username));
    }


}


