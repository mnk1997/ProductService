package com.assignment.userservice.service;

import com.assignment.userservice.exceptions.*;
import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;

public interface IUserService {
    public User signUp(User user) throws IncorrectPhoneNumber;
    public Session login(String email, String password) throws PasswordAndUserNameNotMatchedException, UserNotFoundException;
     Session validateToken(String token , String user) throws UserNotFoundException, NoValidTokenFoundException, SessionExpiredException;
}
