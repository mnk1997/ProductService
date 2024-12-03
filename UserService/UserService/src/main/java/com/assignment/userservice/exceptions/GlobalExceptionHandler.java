package com.assignment.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());

    }
    @ExceptionHandler(IncorrectPhoneNumber.class)
    public ResponseEntity<String> handleIncorrectPhoneNumber(IncorrectPhoneNumber e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());

    }
    @ExceptionHandler(NoRoleSelected.class)
    public ResponseEntity<String> handleNoRoleSelected(NoRoleSelected e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());


    }
    @ExceptionHandler(NoValidTokenFoundException.class)
    public ResponseEntity<String> handleNoValidTokenFoundException(NoValidTokenFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());


    }
    @ExceptionHandler(PasswordAndUserNameNotMatchedException.class)
    public ResponseEntity<String> handlePasswordAndUserNameNotMatchedException(PasswordAndUserNameNotMatchedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());


    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());


    }
    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<String> handleSessionExpiredException(SessionExpiredException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());


    }
    @ExceptionHandler(RoleAlreadyExist.class)
    public ResponseEntity<String> handleRoleAlreadyExist(RoleAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getMessage());
    }
}
