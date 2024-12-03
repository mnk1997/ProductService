package com.assignment.userservice.utils;

import com.assignment.userservice.exceptions.IncorrectPhoneNumber;
import com.assignment.userservice.exceptions.NoRoleSelected;
import com.assignment.userservice.exceptions.RoleNotFoundException;
import com.assignment.userservice.models.Role;
import com.assignment.userservice.repository.RoleRepository;
import com.assignment.userservice.service.IRoleService;
import com.assignment.userservice.service.RoleService;

import java.util.List;

public class UserValidation {

    private IRoleService roleService;


    public static boolean isPhoneNumberValid(String phoneNumber)  throws IncorrectPhoneNumber {
        if(phoneNumber == null || phoneNumber.length() != 10) {
            throw new IncorrectPhoneNumber("Phone number should be 10 digits");
        }
        return true;

    }

}
