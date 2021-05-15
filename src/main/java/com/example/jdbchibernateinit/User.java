package com.example.jdbchibernateinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
