package com.assignment.productservice.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "mentor_joined_table")
public class Mentor extends User{
    String active;

}
