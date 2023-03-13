package com.crud.crudpostgres.services;

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

  public ResponseEntity<Product> getProductById(long id) {
    Optional<Product> exist = productRepository.findById(id);

    if (!exist.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      return exist.map(product -> new ResponseEntity<>(product, HttpStatus.FOUND)).get();
    }
  }

  public ResponseEntity<Product> saveProduct(Product product) {
    Optional<Product> exist = productRepository.findByName(product.getName());

   if (exist.isPresent()) {
      return new ResponseEntity<>(HttpStatus.FOUND);
    }
  
    if(product.getId() > 0) {
      productRepository.save(product);
      return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    } else{
      productRepository.save(product);
      return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
  }


  public ResponseEntity<Product> deleteProduct(long id) {
    Optional<Product> exist = productRepository.findById(id);

    if (!exist.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
      productRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
  }
}
