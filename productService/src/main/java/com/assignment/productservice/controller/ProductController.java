package com.assignment.productservice.controller;

import com.assignment.productservice.models.Product;
import com.assignment.productservice.service.IProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private IProductService productService;
    public ProductController(@Qualifier("SelfProductService")IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InstanceNotFoundException {
       return  ResponseEntity.status(HttpStatus.OK).body( productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws  NullPointerException {
      return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws InstanceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.completeUpdate(product));
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws InstanceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) throws InstanceNotFoundException {

            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteById(product.getId()));
    }

    @PatchMapping
    public ResponseEntity<Product> patchProduct(@RequestBody Product product) throws InstanceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.partialUpdate(product));
    }
    //these exception handler acts as filter onlly for this class methods.
    //if we have to make this as a global we have to write in ControllerAdvice.

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> noProductFound()
    {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No instance found");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException()
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Expecting better input");
    }

}
