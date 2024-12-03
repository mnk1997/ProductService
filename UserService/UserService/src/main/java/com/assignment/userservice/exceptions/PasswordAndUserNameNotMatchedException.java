package com.assignment.userservice.exceptions;

public class PasswordAndUserNameNotMatchedException extends Exception{
    public PasswordAndUserNameNotMatchedException(String mesg) {
        super(mesg);

    }
}
