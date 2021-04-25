package com.example.jdbchibernateinit;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

  public Car() {
  }

  public Car(final Integer id, final String manufacturer, final String name) {
    this.id = id;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "manufacturer")
  private String manufacturer;

  @Column(name = "model_name")
  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(final String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Car{" +
        "id=" + id +
        ", manufacturer='" + manufacturer + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
