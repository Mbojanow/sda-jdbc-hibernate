package com.example.jdbchibernateinit;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CarRepository {

    private final EntityManager entityManager;

    public CarRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // CRUD - Read
    public Optional<Car> findById(Integer id) {
        // SELECT * FROM cars WHERE id = ?
        return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    // CRUD - Create
    public Car createCar(Car car) {
        // INSERT INTO cars(id, manufacurer, model_name) VALUES (?, ?, ?)
        entityManager.persist(car);
        return car;
    }

    // CRUD - Update
    public void updateCar(Car car) {
        // UPDATE cars SET id = id, manufacturer = ?, model_name = ? WHERE id = ?
        entityManager.merge(car);
    }

    // CRUD - Delete
    public void delete(Car car) {
        // DELETE FROM cars WHERE id = ?
        entityManager.remove(car);
    }

}
