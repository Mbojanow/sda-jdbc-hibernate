package com.example.jdbchibernateinit.repositories;

import javax.persistence.EntityManager;
import java.util.Optional;

public abstract class BaseCrudRepository<T> {

    protected final EntityManager entityManager;
    private final Class<T> type;

    public BaseCrudRepository(EntityManager entityManager, Class<T> type) {
        this.entityManager = entityManager;
        this.type = type;
    }

    public Optional<T> findById(final Integer id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
