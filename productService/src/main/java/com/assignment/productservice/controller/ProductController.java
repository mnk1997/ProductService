package com.assignment.productservice.controller;

import com.assignment.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    RestTemplate restTemplte;
    public ProductController(RestTemplate restTemplte) {
        this.restTemplte = restTemplte;
    }
    @GetMapping
    @RequestMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        System.out.println("caleed method with id ");
        Product product = restTemplte.getForObject("https://fakestoreapi.com/products/" + id, Product.class);
        if (product == null) {
            return null;
        }
        System.out.println("Product: " + product);
        return product;
    }

    @GetMapping
    public List<Product> getProducts() {
       Product[] products= restTemplte.getForObject("https://fakestoreapi.com/products",Product[].class);
       if(products.length==0){
           System.out.println("No porduct found ");
           return null;
       }
       for(Product product:products){
           System.out.println(product);
       }
       return List.of(products);
    }
}
