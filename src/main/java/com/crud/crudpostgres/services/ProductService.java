package com.crud.crudpostgres.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  public ResponseEntity<Product> saveProduct(Product product) {
    Optional<Product> exist = productRepository.findByName(product.getName());
    HashMap<String, Object> res = new HashMap<>();


    if (exist.isPresent() && product.getId() != exist.get().getId()) {
      res.put("error", true);
      res.put("message", "Product already exists");
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    productRepository.save(product);
    res.put("data", product);
    res.put("message", "Product created successfully");
        return new ResponseEntity<>(product, HttpStatus.CREATED);
  }
}
