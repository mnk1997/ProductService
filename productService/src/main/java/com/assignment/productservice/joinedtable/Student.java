package com.assignment.productservice.joinedtable;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "student_joined_table")
public class Student extends User {
    String batch;
    String startDate;
    String endDate;
}
