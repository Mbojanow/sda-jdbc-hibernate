package com.example.jdbchibernateinit.repositories;

import com.example.jdbchibernateinit.domain.Technology;

import javax.persistence.EntityManager;

public class TechnologyRepository extends BaseCrudRepository<Technology> {
    public TechnologyRepository(EntityManager entityManager) {
        super(entityManager, Technology.class);
    }
}
