package com.example.jdbchibernateinit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.sql.Timestamp;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    final SessionFactory sessionFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();


    try (Session session = sessionFactory.openSession()) {
      NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT SYSDATE(), DATABASE()", Object[].class);
      Object[] singleResult = nativeQuery.getSingleResult();
      Timestamp timestamp = (Timestamp)singleResult[0];
      String dbName = (String)singleResult[1];
      System.out.println(timestamp + " " + dbName);
    }
  }

}
