package com.crud.crudpostgres.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.crudpostgres.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
  
}
