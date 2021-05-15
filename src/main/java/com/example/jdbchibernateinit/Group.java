package com.example.jdbchibernateinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @ManyToMany
    @JoinTable(name = "groups_to_users",
                                                                           //nazwa POLA w tej klasie
        joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
                                                                               // nazwa POLA z @Id w klasie User
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"))
    private List<User> users;
}
