package com.assignment.productservice.service;

import com.assignment.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service("SelfProductService")
@Primary
public class SelfProductService implements IProductService {
    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product findById(Long id) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product deleteById(Long id) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public Product partialUpdate(Product product) {
        return null;
    }

    @Override
    public Product completeUpdate(Product product) {
        return null;
    }
}
