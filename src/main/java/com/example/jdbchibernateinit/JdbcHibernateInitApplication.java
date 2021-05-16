package com.example.jdbchibernateinit;


import com.example.jdbchibernateinit.domain.SdaCourse;
import com.example.jdbchibernateinit.repositories.SdaCourseRepository;

import java.time.LocalDate;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    HibernateUtil.runInTransaction(entityManager -> {
      SdaCourseRepository sdaCourseRepository = new SdaCourseRepository(entityManager);
      sdaCourseRepository.create(new SdaCourse("Java", "Java from scratch", LocalDate.of(2020, 6, 15),
              LocalDate.of(2021, 5, 29), 999));
    });
  }

}
