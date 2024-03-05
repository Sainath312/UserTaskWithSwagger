package com.example.UserTask.controller;

import com.example.UserTask.constants.ApiConstants;
import com.example.UserTask.constants.StringConstants;
import com.example.UserTask.entity.UserEntity;
import com.example.UserTask.helper.HelperMethods;
import com.example.UserTask.model.*;
import com.example.UserTask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Add a new user", description = "This api is used to add new user into DB")
    @ApiResponse(responseCode = "201", description = "User successfully added to the DB", content = {
            @Content(schema = @Schema(implementation = UserModel.class)) })
    @PostMapping(ApiConstants.SAVE_USER)
    public ResponseMessage addUser(@RequestBody @Valid UserModel user) {
        return userService.addUser(user);
    }



    @Operation(summary = "Retrieve User by UserId", description = "Get user object by specifying its id.")
    @GetMapping(ApiConstants.SEARCH_BY_USERID)
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Retrieve User by UserName", description = "Get user object by specifying userName.")
    @GetMapping(ApiConstants.SEARCH_BY_USERNAME)
    public UserEntity getUserByName(@RequestParam SearchByName name) {
        return userService.getUserByName(name);
    }

    @Operation(summary = "Update the user details", description = "Update User details by providing the id.")
    @PutMapping(ApiConstants.UPDATE_USER)
    public ResponseMessage updateUserByName(@RequestParam SearchByName name,
                                            @RequestBody UserModel requestUpdate) {
        return userService.updateUser(name, requestUpdate);
    }

    @Operation(summary = "Retrieve users", description = "Get all users")
    @GetMapping(ApiConstants.GET_ALL_USER)
    public ResponseEntity<Page<UserEntity>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sortBy) {
        Pageable pageable = helperMethods.createPageable(page, size, sortBy); // Create Pageable object with adjusted logic for handling default sort
        Page<UserEntity> users = userService.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Retrieve List of Users", description = "Get of list user object by specifying keyword.")
    @GetMapping(ApiConstants.SEARCH_KEYWORD)
    public List<UserEntity> getUsersBySearchString(@RequestParam String keyword) {
        return userService.findByAnyDetail(keyword);
    }

    @Operation(summary = "Delete customer details", description = "Delete customer object by using its id.")
    @DeleteMapping(ApiConstants.DELETE_USER)
    public ResponseMessage deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }


}