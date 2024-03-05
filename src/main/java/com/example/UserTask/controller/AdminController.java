package com.example.UserTask.controller;

import com.example.UserTask.constants.ApiConstants;
import com.example.UserTask.constants.StringConstants;
import com.example.UserTask.model.AuthRequest;
import com.example.UserTask.model.NewProfile;
import com.example.UserTask.model.ResponseMessage;
import com.example.UserTask.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiConstants.ADMIN)
@Tag(name = StringConstants.ADMIN, description = StringConstants.A_DESCRIPTION)
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Operation(summary = "Register", description = "This api used to Admin to register")
    @PostMapping(ApiConstants.REGISTER) //Register or Create new Profile
    public ResponseMessage createNewProfile(@RequestBody @Valid NewProfile newProfile) {
        return adminService.createAdminProfile(newProfile);
    }

    @Operation(summary = "Authenticate", description = "This api used to Admin login and to generate the token ")
    @PostMapping(ApiConstants.ADMIN_LOGIN)           // create user session and get token
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest request) {
        return adminService.loginUser(request);
    }

    @Operation(summary = "Logout", description = "This api used for Admin logout")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(ApiConstants.LOGOUT)// user session is invalidated
    public ResponseEntity<String> userLogout(@RequestBody AuthRequest request) {
        return adminService.logoutUser(request);
    }

}
