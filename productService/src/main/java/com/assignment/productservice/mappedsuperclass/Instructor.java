package com.assignment.productservice.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "mps_instructor")
public class Instructor extends User {
    String specialization;
}
