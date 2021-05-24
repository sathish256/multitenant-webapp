package com.multitenant.saml.controller;

import com.multitenant.saml.models.Products;
import com.multitenant.saml.models.ProductsUpdateRequest;
import com.multitenant.saml.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    
    @RequestMapping("/")
    public String index() {
        return "Welcome to the CRUD application!!";
    }
    
    @PostMapping("/products")
    public Products addProductPost(@RequestBody Products newProduct) {
        return productRepository.save(newProduct);
    }
    
    @GetMapping("/products/{id}")
    public Optional<Products> getProduct(@PathVariable String id) {
        if (productRepository.existsById(id)) {
            return productRepository.findById(id);
        } else
            return Optional.empty();
    }
    
    @GetMapping("/products/count")
    public long countTotalProducts() {
        return productRepository.count();
    }
    
    @GetMapping("/products/author/{author}")
    public Products getProductByAuthorName(@PathVariable String author) {
        return productRepository.findByAuthor(author);
    }
    
    @DeleteMapping("/products/topic/{topic}/author/{author}")
    public List<Products> deleteByAuthorAndTopic(@PathVariable String topic, @PathVariable String author) {
        return productRepository.deleteBytopicAndAuthor(topic, author);
    }
    
    @DeleteMapping("/products/{id}")
    public void deleteById(@PathVariable String id) {
        productRepository.deleteById(id);
    }
    
    @PutMapping("/products/{idToBeUpdated}")
    public String updateProduct(@PathVariable String idToBeUpdated, @RequestBody ProductsUpdateRequest productsUpdateRequest) {
        
        Optional<Products> mayBeProduct = productRepository.findById(idToBeUpdated)
                .map(products -> productRepository
                        .save(Products
                                .builder()
                                .id(idToBeUpdated)
                                .category(productsUpdateRequest.getTopic())
                                .tags(productsUpdateRequest.getTags())
                                .name(products.getName())
                                .date(products.getDate())
                                .build())
                );
        if (mayBeProduct.isPresent()) {
            return "Product Updated";
        } else {
            return "Product does not exist";
        }
    }
}
