package com.example.jdbchibernateinit;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.sql.Timestamp;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Car.class)
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(UserDetails.class)
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    Transaction transaction = null;
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();

//      UserDetails userDetails = new UserDetails(null, "Ola", "123123123", null);
//      final User user = new User("ala", "Ala", "Alanska", "ala@test.com", userDetails);
//      session.persist(user);

      UserRepository userRepository = new UserRepository(session);
      //userRepository.findWithDetailsById("andrzej");
      // 1 to 1 - hibernate jest 'sprytny' i sam pobiera zależną encję;
      userRepository.findById("ala");

      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }


//    try (Session session = sessionFactory.openSession()) {
//      final NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT SYSDATE(), DATABASE()");
//      final Object[] singleResult = nativeQuery.getSingleResult();
//      Timestamp timestamp = (Timestamp) singleResult[0];
//      String dbName = (String)singleResult[1];
//      System.out.println(timestamp + " " + dbName);
//    }
//
//    CarsQueries carsQueries = new CarsQueries(sessionFactory);
//    carsQueries.insertCarAndSelectAll("Toyota", "Aygo");
  }

}
