package com.assignment.productservice.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "mps_mentor")
public class Mentor extends User {
    String company;
    int avgRating;
}
