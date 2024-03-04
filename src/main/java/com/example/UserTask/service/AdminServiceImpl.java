package com.example.UserTask.service;

import com.example.UserTask.constants.StringConstants;
import com.example.UserTask.entity.AdminEntity;
import com.example.UserTask.entity.Session;
import com.example.UserTask.exceptions.AlreadyExists;
import com.example.UserTask.exceptions.UserNotExists;
import com.example.UserTask.model.AuthRequest;
import com.example.UserTask.model.NewProfile;
import com.example.UserTask.model.ResponseMessage;
import com.example.UserTask.repository.AdminRepository;
import com.example.UserTask.repository.SessionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    public static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ValidationService validationService;

    boolean flag;
    String username;
    Long id;
    Session userSession;


    public ResponseMessage createAdminProfile(NewProfile userInfo) {
        List<AdminEntity> userList = adminRepository.findAll();
        for (AdminEntity user : userList) {
            flag = (user.getUserName().equals(userInfo.getUserName()) ||
                    (user.getEmail().equals(userInfo.getEmail())));
        }
        if (!flag) {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            AdminEntity profile = new AdminEntity(userInfo.getUserName(), userInfo.getEmail(), userInfo.getPassword());
            profile.setRoles(profile.getRoles());
            adminRepository.save(profile);
            logger.info("New Profile Created Successfully With Name" +userInfo.getUserName());
            return new ResponseMessage(StringConstants.ADMIN_ADD,HttpStatus.CREATED);
        }
        logger.warn("User is already is existing in database with name  " +userInfo.getUserName());
        throw new AlreadyExists(StringConstants.USER_EXISTS);

    }

    //Authenticating the User (using userName(or)userEmail and Password)
    public boolean authenticateUser(AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getAdminName(), authRequest.getPassword()));
            // check whether the user credentials are present in database and authenticating
            if (authentication.isAuthenticated()) {
                username = authRequest.getAdminName();
                id = validationService.getUserIdByUsernameOrEmail(username);
                userSession = sessionRepo.findByAdminId(id);
                return true;
            }
        } catch (AuthenticationException e) {
            throw new UserNotExists(StringConstants.USER_NOT_EXISTS);
        }
        return false;
    }

    //User LoginSession
    public ResponseEntity<String> loginUser(AuthRequest authRequest) {
        if (authenticateUser(authRequest)) {
            if (userSession == null) {
                String token = jwtService.generateToken(username);
                Session userSession = new Session();
                userSession.setAdminId(id);
                userSession.setToken(token);
                sessionRepo.save(userSession); // creating user session in database
                return ResponseEntity.status(HttpStatus.OK)
                        .header("Content-Type", "application/json")
                        .body("{\"message\": \"" + StringConstants.LOGIN + "\", \"token\": \"" + token + "\"}");
            } else {
                throw new AlreadyExists(StringConstants.USER_EXISTS);
            }
        } else {
            throw new UserNotExists(StringConstants.USER_NOT_EXISTS);
        }
    }

    //User LogoutSession
    public ResponseEntity<String> logoutUser(AuthRequest authRequest) {
        ResponseEntity<String> response;
        if (authenticateUser(authRequest)) {
            if (userSession != null) {
                try {
                    sessionRepo.deleteById(userSession.getId()); // Delete the user session from the database
                    logger.info("Returned by Session method : User LogOut Successfully");
                    response = ResponseEntity.status(HttpStatus.OK)
                            .header("Content-Type", "application/json")
                            .body("{\"message\": \"" + StringConstants.LOGOUT + ".\"}");
                } catch (Exception e) {
                    // Handle any exceptions that may occur during session deletion
                    logger.warn("Returned by Session Service method : Failed To LogOut");
                    response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .header("Content-Type", "application/json")
                            .body("{\"message\": \"Failed to logout user.\"}");
                }
            } else {
                throw new UserNotExists(StringConstants.NOT_LOGIN);
            }
        } else {
            throw new UserNotExists(StringConstants.USER_NOT_EXISTS);
        }
        return response;
    }
}