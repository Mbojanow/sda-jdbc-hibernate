package com.example.jdbchibernateinit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void runInTransaction(Consumer<EntityManager> consumer) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
    }

    public static <T> T runInTransaction(Function<EntityManager, T> function) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();
            final T result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
            throw new RuntimeException(exp);
        }
    }
}
