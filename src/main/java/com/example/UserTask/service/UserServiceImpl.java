package com.example.UserTask.service;

import com.example.UserTask.constants.StringConstants;
import com.example.UserTask.entity.UserEntity;
import com.example.UserTask.exceptions.AlreadyExists;
import com.example.UserTask.exceptions.NotFoundException;
import com.example.UserTask.exceptions.UserNotExists;
import com.example.UserTask.model.*;
import com.example.UserTask.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "userCache")
@EnableScheduling
public class UserServiceImpl implements UserService {


    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    public UserRepository userRepository;

    @Override
    @Cacheable(value = "userCache", key = "'getAllUsers'", unless = "#result==null")
    public Page<UserEntity> getUsers(Pageable pageable) {
        logger.info("allUsers found");
        System.out.println("--------------------------------");
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(StringConstants.USER_NOT_PRESENT));
    }


    public UserEntity getUserByName(SearchByName name) {
        return userRepository.findByName(name.getName()).orElseThrow(() -> new NotFoundException(StringConstants.USER_NOT_PRESENT));

    }

    public ResponseMessage addUser(UserModel userInfo) {
        try {
            UserEntity profile = new UserEntity(userInfo.getName(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getEmailId(), userInfo.getMobileNumber());
            userRepository.save(profile);
            logger.info("New Profile Created Successfully With Name" + userInfo.getName());
            return new ResponseMessage(StringConstants.CREATED, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.warn("User is already is existing in database with name  " + userInfo.getName());
            throw new AlreadyExists(StringConstants.USER_EXISTS);
        }
    }

    @Override
    public ResponseMessage updateUser(SearchByName details, UserModel requestUpdate) {
        UserEntity user = userRepository.findByName(details.getName()).orElseThrow(() -> new NotFoundException(StringConstants.USER_NOT_PRESENT));
        logger.info("User : " + user + " is Found");
        user.setName(requestUpdate.getName());
        user.setEmail(requestUpdate.getEmailId());
        user.setFirstName(requestUpdate.getFirstName());
        user.setLastName(requestUpdate.getLastName());
        user.setMobileNumber(requestUpdate.getMobileNumber());
        userRepository.save(user);
        logger.info("UserProfile Is Updated");
        return new ResponseMessage(StringConstants.UPDATED, HttpStatus.OK);
    }

    @Override
    public ResponseMessage deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotExists(StringConstants.USER_NOT_PRESENT));
        logger.info(" User Id: " + id + " is  Found");
        userRepository.deleteById(id);
        logger.info(" User Id: " + id + " is Deleted");
        return new ResponseMessage(StringConstants.DELETED, HttpStatus.OK);
    }


    public List<UserEntity> findByAnyDetail(String keyword) {
        if (keyword.length() < 3) {
            throw new NotFoundException(StringConstants.VALID_KEYWORD);
        }
        List<UserEntity> result = userRepository.findByAnyDetail(keyword);
        if (result.isEmpty()) {
            throw new NotFoundException(StringConstants.USER_NOT_PRESENT);
        }
        return result;
    }
}
