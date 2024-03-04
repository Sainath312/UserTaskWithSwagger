package com.example.UserTask.controller;

import com.example.UserTask.constants.ApiConstants;
import com.example.UserTask.constants.StringConstants;
import com.example.UserTask.entity.UserEntity;
import com.example.UserTask.helper.HelperMethods;
import com.example.UserTask.model.*;
import com.example.UserTask.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.USER)
@Tag(name = StringConstants.USER, description = StringConstants.U_DESCRIPTION)
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HelperMethods helperMethods;


    @PostMapping(ApiConstants.SAVE_USER)
    public ResponseMessage addUser(@RequestBody @Valid UserModel user) {
        return userService.addUser(user);
    }

    @GetMapping(ApiConstants.SEARCH_BY_USERID)
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(ApiConstants.SEARCH_BY_USERNAME)
    public UserEntity getUserByName(@RequestParam SearchByName name) {
        return userService.getUserByName(name);
    }

    @PutMapping(ApiConstants.UPDATE_USER)
    public ResponseMessage updateUserByName(@RequestParam SearchByName name,
                                            @RequestBody UserModel requestUpdate) {
        return userService.updateUser(name, requestUpdate);
    }

    @GetMapping(ApiConstants.GET_ALL_USER)
    public ResponseEntity<Page<UserEntity>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sortBy) {

        // Create Pageable object with adjusted logic for handling default sort
        Pageable pageable = helperMethods.createPageable(page, size, sortBy);

        // Call service method with Pageable object
        Page<UserEntity> users = userService.getUsers(pageable);

        // Return ResponseEntity with appropriate status code
        return ResponseEntity.ok(users);
    }

    @GetMapping(ApiConstants.SEARCH_KEYWORD)
    public List<UserEntity> getUsersBySearchString(@RequestParam SearchKeyword keyword) {
        return userService.findByAnyDetail(keyword);
    }

    @DeleteMapping(ApiConstants.DELETE_USER)
    public ResponseMessage deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}