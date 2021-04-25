package com.example.jdbchibernateinit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.sql.Timestamp;
import java.util.List;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    final SessionFactory sessionFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();


    try (Session session = sessionFactory.openSession()) {
      NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT SYSDATE(), DATABASE()");
      Object[] singleResult = nativeQuery.getSingleResult();
      Timestamp timestamp = (Timestamp)singleResult[0];
      String dbName = (String)singleResult[1];
      System.out.println(timestamp + " " + dbName);

      Transaction transaction = session.beginTransaction();
      NativeQuery insertQuery = session.createNativeQuery("INSERT INTO cars(manufacturer, model_name) VALUES (:manufacturer, :name)");
      insertQuery.setParameter("manufacturer", "Toyota");
      insertQuery.setParameter("name", "Corolla");
      int rowsInserted = insertQuery.executeUpdate();


      List<Object[]> resultList = session.createNativeQuery("SELECT * FROM cars").getResultList();
      for (var row : resultList) {
        System.out.println(row[0] + " " + row[1] + " " + row[2]);
      }

      if (rowsInserted == 1) {
        transaction.commit();
      } else {
        transaction.rollback();
      }
    }
  }

}
