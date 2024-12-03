package com.assignment.userservice.controller;

import com.assignment.userservice.dto.RoleRequestDto;
import com.assignment.userservice.exceptions.RoleAlreadyExist;
import com.assignment.userservice.models.Role;
import com.assignment.userservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RolesController {
    private final RoleService roleService;
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleRequestDto role) throws RoleAlreadyExist {
        Role roleEntity=roleService.saveNewRole(role.getRoleName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleEntity.getValue());

    }
}
