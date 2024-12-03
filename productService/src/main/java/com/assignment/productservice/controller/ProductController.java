package com.assignment.productservice.controller;

import com.assignment.productservice.dto.TokenValidateRequestDto;
import com.assignment.productservice.dto.TokenValidateResponseDto;
import com.assignment.productservice.exception.ProductNotFoundException;
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
    private RestTemplate restTemplate;
    public ProductController(@Qualifier("SelfProductService")IProductService productService,RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @RequestMapping("/{username}/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id,@PathVariable("username") String userName, @RequestHeader String tokenId) throws ProductNotFoundException {

        try{
            ResponseEntity<TokenValidateResponseDto> session=restTemplate.postForEntity("http://localhost:8080/user/validateToken",TokenValidateRequestDto.from(tokenId,userName),TokenValidateResponseDto.class );
            return  ResponseEntity.status(HttpStatus.OK).body( productService.findById(id));
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws  NullPointerException {
      return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.completeUpdate(product));
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteProduct(@RequestBody Product product) throws ProductNotFoundException {


        try {
            productService.deleteById(product.getId());
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @PatchMapping
    public ResponseEntity<Product> patchProduct(@RequestBody Product product) throws ProductNotFoundException {
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
