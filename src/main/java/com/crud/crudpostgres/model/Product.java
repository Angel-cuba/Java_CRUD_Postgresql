package com.crud.crudpostgres.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String name;
  private float price;
  private LocalDate date;
  @Transient
  private int antiquity;
 


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
