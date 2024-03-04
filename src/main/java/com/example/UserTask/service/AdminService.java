package com.example.UserTask.service;

import com.example.UserTask.model.AuthRequest;
import com.example.UserTask.model.NewProfile;
import com.example.UserTask.model.ResponseMessage;
import org.springframework.http.ResponseEntity;

public interface AdminService {


    ResponseEntity<String> loginUser(AuthRequest request) ;

    ResponseEntity<String> logoutUser(AuthRequest request);

    ResponseMessage createAdminProfile(NewProfile newProfile);
}
