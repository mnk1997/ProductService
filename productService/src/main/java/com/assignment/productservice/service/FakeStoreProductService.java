package com.assignment.productservice.service;

import com.assignment.productservice.dto.FakeStoreProductDto;
import com.assignment.productservice.models.Category;
import com.assignment.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FakeStoreProductService implements IProductService {

    RestTemplate restTemplte;

    public FakeStoreProductService(RestTemplate restTemplte) {
        this.restTemplte = restTemplte;
    }

    @Override
    public List<Product> findAll() throws NullPointerException {
        List<Product> finalProducts = new ArrayList<>();
        FakeStoreProductDto[] products= restTemplte.getForObject("https://fakestoreapi.com/products",FakeStoreProductDto[].class);
        if(products==null || products.length==0){
            throw new NullPointerException("Unable to fetch products");
        }
        for(FakeStoreProductDto product:products){
           finalProducts.add(convertFakeStoreProductDtoToProduct(product));
        }
        return finalProducts;
    }

    @Override
    public Product findById(Long id) throws InstanceNotFoundException {
        System.out.println("caleed method with id ");
        FakeStoreProductDto product = restTemplte.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if (product == null) {
           throw new InstanceNotFoundException("No product found for given entity"+id);
        }
        System.out.println("Product: " + product);
        return convertFakeStoreProductDtoToProduct(product);
    }

    @Override
    public Product save(Product product) {
        System.out.println("Post method callled ");
        return convertFakeStoreProductDtoToProduct(restTemplte.postForEntity("https://fakestoreapi.com/products",convertProductToFakeStoreProductDto(product),FakeStoreProductDto.class).getBody());
    }

    @Override
    public void deleteById(Long id) throws InstanceNotFoundException {

        Product product =  findById(id);
        if(id<=0 || product==null)
            throw new InstanceNotFoundException("No product found for given id");

        restTemplte.delete("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

    }

    @Override
    public Product partialUpdate(Product product) {
      return convertFakeStoreProductDtoToProduct(
              Objects.requireNonNull(restTemplte.patchForObject("https://fakestoreapi.com/products/" + product.getId(), convertProductToFakeStoreProductDto(product), FakeStoreProductDto.class))
      );
    }

    @Override
    public Product completeUpdate(Product product) {

        RequestCallback requestCallback = restTemplte.httpEntityCallback(product);
        HttpMessageConverterExtractor<Product> responseExtractor = new HttpMessageConverterExtractor<Product>(Product.class, restTemplte.getMessageConverters());
      Product updatedProduct=  restTemplte.execute("https://fakestoreapi.com/products/"+product.getId(), HttpMethod.PUT, requestCallback, responseExtractor);
        return updatedProduct;

       // restTemplte.put("https://fakestoreapi.com/products/" + product.getId(), product, Product.class);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product1=new Product();
        product1.setId(fakeStoreProductDto.getId());
        product1.setTitle(fakeStoreProductDto.getTitle());
        product1.setDescription(fakeStoreProductDto.getDescription());
        Category c=new Category();
        c.setDescription(fakeStoreProductDto.getCategory());
        product1.setCategory(c);
        return product1;

    }

    private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStroeProductDto=new FakeStoreProductDto();
        fakeStroeProductDto.setId(product.getId());
        fakeStroeProductDto.setTitle(product.getTitle());
        fakeStroeProductDto.setDescription(product.getDescription());
        fakeStroeProductDto.setCategory(product.getCategory().getDescription());
        return fakeStroeProductDto;

    }

}
