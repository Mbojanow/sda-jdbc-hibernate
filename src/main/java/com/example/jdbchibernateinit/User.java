package com.example.jdbchibernateinit;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // jeżeli decydujemy się na PERSIST - prawie zawsze dodajemy też MERGE
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private UserDetails userDetails;

    //user może mieć wiele produktów
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Product> products;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "users")
    private List<Group> groups;
}
