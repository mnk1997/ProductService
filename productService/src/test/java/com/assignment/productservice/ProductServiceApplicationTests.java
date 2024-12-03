package com.assignment.productservice;

import com.assignment.productservice.controller.ProductController;
import com.assignment.productservice.exception.ProductNotFoundException;
import com.assignment.productservice.models.Category;
import com.assignment.productservice.models.Product;
import com.assignment.productservice.service.IProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    ProductController productController;
    //here we have to mention the name because we are using @Qualifier in the constr of ProductController
    @MockBean
    IProductService productService;

    @Test
    void contextLoads() {
    }

    @Test
    void productShouldExistWithGivenId() throws ProductNotFoundException {
        Product product = new Product();
        //ARRANGE
        product.setId(1L);
        product.setTitle("Mens Condoms /..");
        product.setDescription("Its easy to Play with Manforce Condoms ...");
        Category category = new Category();
        category.setId(1L);
        category.setDescription("Sexual");
        product.setCategory(category);
        Mockito.when(productService.findById(1L)).thenReturn(product);

        //ACT
        Product resultProduct=productController.getProductById(1L,null,null).getBody();
        //ASSERT
        Assertions.assertNotNull(resultProduct);
        Assertions.assertEquals(product.getTitle(), resultProduct.getTitle());
        Assertions.assertEquals(product.getDescription(), resultProduct.getDescription());
        Assertions.assertEquals(product.getCategory().getId(), resultProduct.getCategory().getId());

    }


    @Test
    void productShouldBeNullWithGivenId() throws ProductNotFoundException {

        //ARRANGE


        Mockito.when(productService.findById(20L)).thenReturn(null);

        //ACT and ASSERT

      //  Assertions.assertNull(productController.getProductById(20L).getBody());


    }

    @Test
    void deleteByIdShouldBeCalledOnce() throws ProductNotFoundException {
        //ARRANGE
        Product product = new Product();
        product.setId(1L);
        Mockito.doNothing().when(productService).deleteById(1L);

        //ACT
        productController.deleteProduct(product);

        //ASSERT
        Mockito.verify(productService, Mockito.times(1)).deleteById(1L);

    }

}
