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
      CarRepository carRepository = new CarRepository(session);
      carRepository.findById(1).ifPresent(System.out::println);

      // managed - dzięki temu zmiany encji NIE wymagaja wywołania merge na EntityManager
      final Car car = carRepository.createCar(new Car(null, "Renault", "Clio"));
      System.out.println(car);
      car.setModelName("Megane");
      //carRepository.updateCar(car);
      //carRepository.delete(car);

      System.out.println("Number of records " + carRepository.findAll().size());
      System.out.println("Toyota cars number " + carRepository.findCarsByProducer("Toyota"));
      carRepository.deleteById(1);

      final UserDetails userDetails = new UserDetails(null, "andrzej", "123123123");
      session.persist(userDetails);
      final User user = new User("andrzej", "andrzej", "andrzejewski", "andrzej@test.com",
              userDetails);
      session.persist(user);

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
