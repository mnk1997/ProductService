package com.assignment.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@MappedSuperclass
@Data
public class BaseModel {

    @Id
    long id ;

}
