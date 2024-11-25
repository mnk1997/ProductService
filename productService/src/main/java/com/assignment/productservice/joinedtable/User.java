package com.assignment.productservice.joinedtable;


import jakarta.persistence.*;


@Entity(name = "user_joined_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class User  extends Id {

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String dob;
}
