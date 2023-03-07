package com.crud.crudpostgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crudpostgres.model.Product;
import com.crud.crudpostgres.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
  
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> listOfProducts() {
    return productService.getProducts();
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }
}
