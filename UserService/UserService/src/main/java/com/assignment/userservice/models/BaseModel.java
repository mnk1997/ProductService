package com.assignment.userservice.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP) // Optional: to specify how the Date is stored in DB
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    public void prePersist() {
        // Set the current date and time before persisting the entity
        if (createdAt == null) {
            createdAt = new Date();
        }
    }
}
