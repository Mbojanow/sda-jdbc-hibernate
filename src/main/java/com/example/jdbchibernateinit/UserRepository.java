package com.example.jdbchibernateinit;

import javax.persistence.EntityManager;
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
}
