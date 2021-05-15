package com.example.jdbchibernateinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public Product(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;
}
