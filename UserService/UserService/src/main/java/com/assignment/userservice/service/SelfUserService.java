package com.assignment.userservice.service;

import com.assignment.userservice.exceptions.*;
import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;
import com.assignment.userservice.repository.UserRepository;
import com.assignment.userservice.utils.UserValidation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@Service
public class SelfUserService implements  IUserService{
    private final RoleService roleService;
    private UserRepository userRepo;

    private PasswordEncoder passwordEncoder;

    private TokenService tokenService;
    public SelfUserService(PasswordEncoder passwordEncoder, UserRepository userRepo, RoleService roleService,TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }

    @Override
    public User signUp(User user) throws IncorrectPhoneNumber, RoleNotFoundException, NoRoleSelected {


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //validate user entry
            //like user phone number should be equal to 10 digit
        UserValidation.isPhoneNumberValid(user.getPhoneNumber());
            //all the roles are present or not
          roleService.isRolesValid(user.getRoles());

          //set uniqiue userName for the user
        StringBuilder username=new StringBuilder(user.getFirstName());
        username.append(user.getPhoneNumber().substring(6));
          user.setUserName(username.toString());
       return  userRepo.save(user);
    }

    @Override
    public Session login(String username, String password) throws PasswordAndUserNameNotMatchedException, UserNotFoundException {
      Optional<User> user=  userRepo.findByUserName(username);
      if(user.isPresent()){
          if(passwordEncoder.matches(password,user.get().getPassword())){
              Session token;
              //fetch if already token is valid or not
              try{
                token  =tokenService.findNonExpiredTokenForGivenUserName(user.get());
                return token;
              }catch(NoValidTokenFoundException e){
                  //we have to create new token..
              }
              token=new Session();
              token.setUser(user.get());
              token.setExpiryAt(Instant.now().getEpochSecond()+15*60);
              //generat some randaom string token
                 token.setToken(username+Instant.now().getEpochSecond());
            token=  tokenService.saveSession(token);
            return token;




          }
          else
              throw new PasswordAndUserNameNotMatchedException("Mismatch combo of username and password");



      }
      else
         throw new UserNotFoundException("usernot found with given id ");



    }

    @Override
    public Session validateToken(String token, String username) throws UserNotFoundException, NoValidTokenFoundException, SessionExpiredException {
        Optional<User> user=  userRepo.findByUserName(username);
        if(user.isPresent()){
            return tokenService.validateToken(token,user.get());
        }else
            throw new UserNotFoundException("No user found with the given username");
    }
}
