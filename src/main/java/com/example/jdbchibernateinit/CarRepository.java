package com.example.jdbchibernateinit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

// DAO - CarDAO - Data Access Object
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

    public List<Car> findAll() {
        return entityManager.createQuery("SELECT c FROM cars c", Car.class)
                .getResultList();
    }

    public List<Car> findCarsByProducer(String producer) {
        // c.manufacturer -> wskazuje nazwę pola java
        return entityManager.createQuery("SELECT c FROM cars c WHERE c.manufacturer = :producer", Car.class)
                .setParameter("producer", producer)
                .getResultList();
    }

    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM cars c WHERE c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
