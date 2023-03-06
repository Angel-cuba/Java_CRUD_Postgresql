package com.crud.crudpostgres.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.crudpostgres.interfaces.ProductRepository;
import com.crud.crudpostgres.model.Product;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;
  
  public List<Product> getProducts() {
    return productRepository.findAll();
  }
}
