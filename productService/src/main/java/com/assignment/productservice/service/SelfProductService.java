package com.assignment.productservice.service;

import com.assignment.productservice.models.Product;
import com.assignment.productservice.repository.CategoryRepository;
import com.assignment.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service("SelfProductService")
@Primary
public class SelfProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws InstanceNotFoundException {
        return productRepository.findById(id).orElseThrow(InstanceNotFoundException::new);

    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) throws InstanceNotFoundException {

         productRepository.deleteById(id);

    }

    @Override
    public Product partialUpdate(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product completeUpdate(Product product) {
        return productRepository.save(product);
    }
}
