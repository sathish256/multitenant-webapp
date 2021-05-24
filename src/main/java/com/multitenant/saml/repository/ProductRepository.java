package com.multitenant.saml.repository;

import com.multitenant.saml.models.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Products, String> {
    
    Products findByAuthor(String author);
    
    List<Products> deleteBytopicAndAuthor(String title, String author);
}