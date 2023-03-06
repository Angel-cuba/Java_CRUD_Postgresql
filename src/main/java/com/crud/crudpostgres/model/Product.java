package com.crud.crudpostgres.model;

import java.time.LocalDate;

public class Product {
  private long id;
  private String name;
  private String price;
  private LocalDate date;
  private int antiquity;

  public Product() {
  }

  public Product(long id, String name, String price, LocalDate date, int antiquity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.date = date;
    this.antiquity = antiquity;
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

  public String getPrice() {
    return price;
  } 

  public void setPrice(String price) {
    this.price = price;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getAntiquity() {
    return antiquity;
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
