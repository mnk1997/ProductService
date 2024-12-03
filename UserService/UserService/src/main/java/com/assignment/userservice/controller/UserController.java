package com.assignment.userservice.controller;
import com.assignment.userservice.dto.*;
import com.assignment.userservice.exceptions.*;
import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;
import com.assignment.userservice.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService  userService;
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserSignUpResponseDto> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) throws IncorrectPhoneNumber {
        User user=userService.signUp(UserSignUpRequestDto.toUser(userSignUpRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserSignUpResponseDto.fromUser(user));

    }

    @PostMapping("/login")
    public ResponseEntity<UserSignInResponseDto> signIn(@RequestBody UserSignInRequestDto user) throws UserNotFoundException, PasswordAndUserNameNotMatchedException {
        Session token=userService.login(user.getUserName(),user.getPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body(UserSignInResponseDto.fromSession(token));
    }

    @PostMapping("/validateToken")
    public ResponseEntity<TokenValidateResponseDto> validateToken(@RequestBody TokenValidateRequestDto token) throws UserNotFoundException, NoValidTokenFoundException, SessionExpiredException {
        Session session =userService.validateToken(token.getToken(),token.getUserName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(TokenValidateResponseDto.fromToken(session));
    }

}
