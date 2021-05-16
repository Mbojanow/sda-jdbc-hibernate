package com.example.jdbchibernateinit.repositories;

import com.example.jdbchibernateinit.domain.SdaCourse;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SdaCourseRepository extends BaseCrudRepository<SdaCourse> {
    public SdaCourseRepository(EntityManager entityManager) {
        super(entityManager, SdaCourse.class);
    }

    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM sda_courses sc WHERE sc.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<SdaCourse> findCoursesWithPriceRange(Integer minPriceInclusive, Integer maxPriceInclusive) {
        return entityManager.createQuery("SELECT sc FROM sda_courses sc WHERE sc.price BETWEEN :minPrice AND :maxPrice", SdaCourse.class)
                .setParameter("minPrice", minPriceInclusive)
                .setParameter("maxPrice", maxPriceInclusive)
                .getResultList();
    }

    public List<SdaCourse> findCoursesByNames(String ...names) {
        return entityManager.createQuery("SELECT sc FROM sda_courses sc WHERE sc.name IN (:names)", SdaCourse.class)
                .setParameter("names", Arrays.asList(names))
//                .setMaxResults(10)
//                .setFirstResult(20)
                .getResultList();
    }

    public Optional<SdaCourse> findCourseByIdWithTechnologies(Integer id) {
        List<SdaCourse> courses = entityManager.createQuery("SELECT sc FROM sda_courses sc LEFT JOIN FETCH sc.technologies t WHERE sc.id = :id", SdaCourse.class)
                .setParameter("id", id)
                .getResultList();
        if (courses.size() == 1) {
            return Optional.of(courses.get(0));
        }
        return Optional.empty();
    }

    public Double findAverageCoursePrice() {
        return (Double) entityManager.createQuery("SELECT AVG(sc.price) FROM sda_courses sc")
                .getSingleResult();
    }

}
