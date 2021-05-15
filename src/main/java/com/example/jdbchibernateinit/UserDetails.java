package com.example.jdbchibernateinit;

import lombok.*;

import javax.persistence.*;

@Entity(name = "users_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone_number")
    private String phoneNumber;

    // userDetails - NAZWA POLA w obiekcie User
    // relacja jest dwustronna - problem podczas generowania metod toString, equals i hashcode -> StackOverflow
    // rozw - po 1 stronie rozerwać niekonczaca sie pętle
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "userDetails")
    private User user;
}
