package com.assignment.userservice.repository;

import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByExpiryAtAfterAndUser(Long expiryAt, User user);
    Optional<Session> findByTokenAndUser(String token, User user);
}
