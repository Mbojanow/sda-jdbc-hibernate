package com.example.jdbchibernateinit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "sda_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SdaCourse {

    public SdaCourse(String name, String description, LocalDate startDate, LocalDate endDate, Integer price) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price")
    private Integer price;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "course_to_technology",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), // id w tej klasie
            inverseJoinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id")) // id w klasie Technology
    private List<Technology> technologies = new ArrayList<>();

    public void addTechnology(Technology technology) {
        technologies.add(technology);
    }
}
