package com.example.jdbchibernateinit.domain;

import com.example.jdbchibernateinit.model.TechnologyType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "technology_name")
    private String name;

    @Column(name = "version")
    private String version;

    @Column(name = "creation_date")
    private LocalDate localDate;

    @Column(name = "supporting_company_name")
    private String supportingCompanyName;

    @Enumerated(EnumType.STRING)
    private TechnologyType technologyType;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToMany(mappedBy = "technologies")
//    private List<SdaCourse> courses;
}
