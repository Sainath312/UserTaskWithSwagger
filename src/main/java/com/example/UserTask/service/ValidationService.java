package com.example.UserTask.service;

import com.example.UserTask.entity.AdminEntity;
import com.example.UserTask.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    @Autowired
    AdminRepository userInfoRepo;

    public Long getUserIdByUsernameOrEmail(String usernameOrEmail) {
        // Check if the input is a valid email address
        if (isValidEmail(usernameOrEmail)) {
            return userInfoRepo.findByEmail(usernameOrEmail)
                    .map(AdminEntity::getId)
                    .orElse(null);
        } else {
            // Assuming username is unique, directly search by username
            return userInfoRepo.findByUserName(usernameOrEmail)
                    .map(AdminEntity::getId)
                    .orElse(null);
        }
    }

    private boolean isValidEmail(String email) {
        // Define a simple regex pattern for email validation
        String emailRegex ="^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
