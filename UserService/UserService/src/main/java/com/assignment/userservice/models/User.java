package com.assignment.userservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import org.springframework.data.annotation.Persistent;

import java.util.List;

@Data
@Entity(name = "user_table")
public class User extends BaseModel {
    private String email;
    private String password;
    private String firstName;
    private String phoneNumber;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;
    private String userName;

}
