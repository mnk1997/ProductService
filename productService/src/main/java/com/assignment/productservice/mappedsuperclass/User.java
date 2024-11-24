package com.assignment.productservice.mappedsuperclass;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {

    @Id
    Long id;
    String name;
    String email;
    String password;

}
