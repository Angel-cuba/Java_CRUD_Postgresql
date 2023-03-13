package com.crud.crudpostgres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable long id) {
      ResponseEntity<Product> existingProduct = productService.getProductById(id);

    if(existingProduct == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    return existingProduct;
    }

  @PostMapping("/create")
  public ResponseEntity<String> createProduct(@RequestBody Product product) {
   productService.saveProduct(product);
    return new ResponseEntity<String>("Product created",HttpStatus.CREATED);
  }

  @PutMapping("/update")
  public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    if(product.getId() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Id given is not valid");
    }
    return productService.saveProduct(product);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return new ResponseEntity<String>("Deleted",HttpStatus.ACCEPTED);
  }
}
