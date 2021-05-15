package com.example.jdbchibernateinit;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(Car.class)
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(UserDetails.class)
            .addAnnotatedClass(Product.class)
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    Transaction transaction = null;
    try (Session session = sessionFactory.openSession()) {
      transaction = session.beginTransaction();

//      UserDetails userDetails = new UserDetails(null, "Ola", "123123123", null);
//      final User user = new User("ala", "Ala", "Alanska", "ala@test.com", userDetails);
//      session.persist(user);

//      UserRepository userRepository = new UserRepository(session);
//      //userRepository.findWithDetailsById("andrzej");
//      // 1 to 1 - hibernate jest 'sprytny' i sam pobiera zależną encję;
//      userRepository.findById("ala");

//      User user = new User("Michal", "Michal", "Michal", "michal@test.com", null, new ArrayList<>());
//      Product product = new Product("p1", "Product 1");
//      session.persist(product);
//      // powiązanie user <-> product
//      user.setProducts(List.of(product));
//      session.persist(user);

      UserRepository userRepository = new UserRepository(session);
      final Optional<User> user = userRepository.findByIdWithProducts("Michal");
      System.out.println(user.get().getProducts());

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
