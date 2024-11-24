package com.assignment.productservice.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "mps_student")
public class Student extends User {
    String course;
    String batch;

}
