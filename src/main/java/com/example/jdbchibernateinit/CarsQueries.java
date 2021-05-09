package com.example.jdbchibernateinit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class CarsQueries {

    private final SessionFactory sessionFactory;

    public CarsQueries(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertCarAndSelectAll(String manufacturer, String modelName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            NativeQuery nativeQuery = session.createNativeQuery(
                    "INSERT INTO cars(manufacturer, model_name) VALUES(:manufacturer, :name)");
            nativeQuery.setParameter("manufacturer", manufacturer);
            nativeQuery.setParameter("name", modelName);
            int insertedCount = nativeQuery.executeUpdate();
            if (insertedCount == 0) {
                transaction.rollback();
            }

            final NativeQuery selectQuery = session.createNativeQuery("SELECT * FROM cars");
            final List<Object[]> resultList = selectQuery.getResultList();
            resultList.forEach(columns -> System.out.println(columns[0] + " " + columns[1] + " " + columns[2]));
            transaction.commit();
        }
    }
}
