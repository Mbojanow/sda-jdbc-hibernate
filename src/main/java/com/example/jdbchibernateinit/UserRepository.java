package com.example.jdbchibernateinit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findWithDetailsById(String username) {
        try {
            return Optional.of(entityManager.createQuery("SELECT u FROM users u LEFT JOIN u.userDetails WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (Exception exp) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(String username) {
        return Optional.ofNullable(entityManager.find(User.class, username));
    }

    // pobierz użytkownika o danym id wraz z produktami, gdy na encji jest domyślny fetch type - LAZY
    public Optional<User> findByIdWithProducts(String username) {
        // LEFT JOIN FETCH -> wymusza TYLKO podczas tego zapytania pobranie zależnej encji
        final List<User> users = entityManager.createQuery("SELECT u FROM users u LEFT JOIN FETCH u.products WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if (users.size() == 1) {
            return Optional.of(users.get(0));
        }
        return Optional.empty();
    }
}
