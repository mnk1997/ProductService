package com.assignment.userservice.service;

import com.assignment.userservice.exceptions.NoValidTokenFoundException;
import com.assignment.userservice.exceptions.SessionExpiredException;
import com.assignment.userservice.exceptions.UserNotFoundException;
import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;

public interface ITokenService {
   Session findNonExpiredTokenForGivenUserName(User user)throws NoValidTokenFoundException;
   Session validateToken(String token, User user)throws SessionExpiredException,NoValidTokenFoundException;

   Session saveSession(Session token);
}
