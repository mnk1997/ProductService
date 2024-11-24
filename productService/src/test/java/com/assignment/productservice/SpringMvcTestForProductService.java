package com.assignment.productservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import com.assignment.productservice.controller.ProductController;
import com.assignment.productservice.models.Product;
import com.assignment.productservice.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
public class SpringMvcTestForProductService {

    @Autowired
    MockMvc mockMvc;

    @MockBean(name = "SelfProductService")
    IProductService productService;

    private Product mockProduct;
    @BeforeEach
    void setUp() {
        // Set up a mock product to return in the tests
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setTitle("Condoms");
        mockProduct.setDescription("This is a sample product.");
    }


    @Test
    void shouldReturnHttpStatusOk() throws Exception {

        //ARRANGE
         when(productService.findById(1L)).thenReturn(mockProduct);
         //ACT and ASSERT
        this.mockMvc.perform(get("/products/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Condoms"));
    }


}
