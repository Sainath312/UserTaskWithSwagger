package com.example.UserTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResponseMessage {
    private String message;
    private HttpStatus status;
}
