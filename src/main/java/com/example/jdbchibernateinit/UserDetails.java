package com.example.jdbchibernateinit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user_details")
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

  @OneToOne(mappedBy = "userDetails")
  private User user;
}
