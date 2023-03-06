package com.crud.crudpostgres.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crud.crudpostgres.model.Product;

@Service
public class ProductService {
  
  public List<Product> getProducts() {
    return List.of(
      new Product(1L, "Product 1", "R$ 10,00", LocalDate.now(), 1),
      new Product(2L, "Product 2", "R$ 20,00", LocalDate.now(), 2),
      new Product(3L, "Product 3", "R$ 30,00", LocalDate.now(), 3),
      new Product(4L, "Product 4", "R$ 40,00", LocalDate.now(), 4),
      new Product(5L, "Product 5", "R$ 50,00", LocalDate.now(), 5),
      new Product(6L, "Product 6", "R$ 60,00", LocalDate.now(), 6),
      new Product(7L, "Product 7", "R$ 70,00", LocalDate.now(), 7),
      new Product(8L, "Product 8", "R$ 80,00", LocalDate.now(), 8),
      new Product(9L, "Product 9", "R$ 90,00", LocalDate.now(), 9),
      new Product(10L, "Product 10", "R$ 100,00", LocalDate.now(), 10)
    );
  }
}
