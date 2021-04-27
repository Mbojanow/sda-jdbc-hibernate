package com.example.jdbchibernateinit;

import javax.persistence.EntityManager;

public class UserRepository {

  private final EntityManager entityManager;

  public UserRepository(final EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public User findUserById(String userId) {
    return entityManager.createQuery("SELECT u FROM users u left join u.userDetails WHERE u.username = :userId", User.class)
        .getSingleResult();
  }

  public void save(User user) {
    entityManager.persist(user);
  }
}
