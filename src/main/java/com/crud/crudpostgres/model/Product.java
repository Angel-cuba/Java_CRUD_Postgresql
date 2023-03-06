package com.crud.crudpostgres.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private float price;
  private LocalDate date;
  @Transient
  private int antiquity;

  public Product() {
  }

  public Product(long id, String name, Float price, LocalDate date) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
  }

  public Product(String name, Float price, LocalDate date) {
    this.name = name;
    this.price = price;
    this.date = date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

 
  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getAntiquity() {
    return Period.between(date, LocalDate.now()).getYears();
  }

  public void setAntiquity(int antiquity) {
    this.antiquity = antiquity;
  }

  @Override
  public String toString() {
    return "Product [date=" + date + ", id=" + id + ", name=" + name + ", price=" + price + ", timeOfDelivery="
        + antiquity + "]";
  }

}
