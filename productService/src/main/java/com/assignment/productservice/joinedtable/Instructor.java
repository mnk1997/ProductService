package com.assignment.productservice.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "instructor_joined_table")
public class Instructor extends User{
    String discipline;
    String qualification;
    String yearsOfExp;
}
