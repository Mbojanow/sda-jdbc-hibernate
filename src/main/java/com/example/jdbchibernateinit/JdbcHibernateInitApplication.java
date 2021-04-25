package com.example.jdbchibernateinit;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    final SessionFactory sessionFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();
  }

}
