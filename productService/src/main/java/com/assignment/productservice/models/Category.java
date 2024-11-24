package com.assignment.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

@Data
@Entity
public class Category  extends BaseModel{
    String description;
}
