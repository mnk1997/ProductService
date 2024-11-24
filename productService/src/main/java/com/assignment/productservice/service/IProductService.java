package com.assignment.productservice.service;

import com.assignment.productservice.models.Product;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface IProductService {
    public List<Product> findAll();
    public Product findById(Long id) throws InstanceNotFoundException;
    public Product save(Product product);
    public Product deleteById(Long id) throws InstanceNotFoundException;
    public Product partialUpdate(Product product);
    public Product completeUpdate(Product product);
}
