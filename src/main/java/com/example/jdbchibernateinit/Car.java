package com.example.jdbchibernateinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "cars")
//@Table(name = "cars") -> zmienia sposob wskazania nazwy w HQL
@Data // gettery, settery, equals i hashcode
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue // domyślnie AUTO -> wybiera pomiędzy IDENTITY, SEQUENCE, TABLE na podstawie drivera -> SEQUENCE dla MySQLA
    private Integer id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model_name")
    private String modelName;
}
