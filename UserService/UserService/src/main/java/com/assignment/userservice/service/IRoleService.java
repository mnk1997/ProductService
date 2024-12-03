package com.assignment.userservice.service;

import com.assignment.userservice.exceptions.RoleAlreadyExist;
import com.assignment.userservice.exceptions.RoleNotFoundException;
import com.assignment.userservice.models.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Optional<String> validateRole(String role) throws RoleNotFoundException;
    Role saveNewRole(String role) throws RoleNotFoundException, RoleAlreadyExist;
    boolean isRolesValid(List<Role> roles);
}
