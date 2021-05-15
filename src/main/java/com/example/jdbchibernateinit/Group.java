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
    private Integer gid;

    @Column(name = "name_short")
    private String shortName;

    @Column(name = "long_name")
    private String longName;

    @ManyToMany
//    @JoinTable(name = "groups_to_users",
//            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"))
    private List<User> users;
}
