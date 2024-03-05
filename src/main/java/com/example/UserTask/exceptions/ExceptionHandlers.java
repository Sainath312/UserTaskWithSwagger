package com.example.UserTask.exceptions;

import com.example.UserTask.model.ExceptionModel;
import com.example.UserTask.model.GetMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleEmpDetailsExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(Exception exception, WebRequest res) {
        ExceptionModel ex = new ExceptionModel(HttpStatus.NOT_FOUND.toString(), exception.getMessage(), res.getDescription(false));
        return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadyExists.class})
    public ResponseEntity<Object> handleUserAlreadyExists(Exception exception, WebRequest res) {
        ExceptionModel ex = new ExceptionModel(HttpStatus.CONFLICT.toString(), exception.getMessage(), res.getDescription(false));
        return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserNotExists.class})
    public ResponseEntity<Object> handleUserNotExists(Exception exception, WebRequest res) {
        ExceptionModel ex = new ExceptionModel(HttpStatus.BAD_REQUEST.toString(), exception.getMessage(), res.getDescription(false));
        return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({EmptyList.class})
    public ResponseEntity<Object> handleEmptyList(Exception exception, WebRequest res) {
        GetMessage ex = new GetMessage(HttpStatus.NOT_FOUND.toString(), exception.getMessage(), res.getDescription(false));
        return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);
    }


}

