package com.crud.crudpostgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

  @PostMapping("/create")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return productService.saveProduct(product);
  }

  @PutMapping("/update")
  public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    if(product.getId() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id given is not valid");
    }
    return productService.saveProduct(product);
  }
}
