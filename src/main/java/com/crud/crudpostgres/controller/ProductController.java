package com.crud.crudpostgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
