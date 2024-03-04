package com.example.UserTask.exceptions;

public class UserNotExists extends RuntimeException{
    public UserNotExists(String message) {
        super(message);
    }
}
