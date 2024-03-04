package com.example.UserTask.service;

import com.example.UserTask.entity.UserEntity;
import com.example.UserTask.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserEntity> getUsers(Pageable pageable);

    public UserEntity getUserById(Long id);



    UserEntity getUserByName(SearchByName name);

    ResponseMessage addUser(UserModel user);

    ResponseMessage updateUser(SearchByName name, UserModel requestUpdate);

    ResponseMessage deleteUser(Long id);

    List<UserEntity> findByAnyDetail(SearchKeyword keyword);
}