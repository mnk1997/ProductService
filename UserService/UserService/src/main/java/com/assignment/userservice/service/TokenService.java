package com.assignment.userservice.service;

import com.assignment.userservice.exceptions.NoValidTokenFoundException;
import com.assignment.userservice.exceptions.SessionExpiredException;
import com.assignment.userservice.exceptions.UserNotFoundException;
import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;
import com.assignment.userservice.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;


@Service
public class TokenService implements ITokenService {

    private SessionRepository sessionRepository;

    public TokenService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
    @Override
    public Session findNonExpiredTokenForGivenUserName(User user) throws NoValidTokenFoundException {
        Optional<Session> token=sessionRepository.findByExpiryAtAfterAndUser(Instant.now().getEpochSecond(),user);
        if(token.isEmpty()){
            throw new NoValidTokenFoundException("No valid token found ..Please login again..");
        }
        return token.get();
    }

    @Override
    public Session validateToken(String tokenId,User user) throws SessionExpiredException,NoValidTokenFoundException {
        Optional<Session> token=sessionRepository.findByTokenAndUser(tokenId,user);
        if(token.isEmpty()){
            throw new NoValidTokenFoundException("No token found..Please login again");
        }
        if(token.get().getExpiryAt()<Instant.now().getEpochSecond()){
            throw new SessionExpiredException("Session expired...");
        }
        return token.get();


    }

    @Override
    public Session saveSession(Session token) {
        return sessionRepository.save(token);
    }
}
