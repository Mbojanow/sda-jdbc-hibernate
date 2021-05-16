package com.example.jdbchibernateinit.services;

import com.example.jdbchibernateinit.domain.SdaCourse;
import com.example.jdbchibernateinit.domain.Technology;
import com.example.jdbchibernateinit.repositories.SdaCourseRepository;
import com.example.jdbchibernateinit.repositories.TechnologyRepository;

public class TechnologyService {

    private final SdaCourseRepository sdaCourseRepository;
    private final TechnologyRepository technologyRepository;

    public TechnologyService(SdaCourseRepository sdaCourseRepository, TechnologyRepository technologyRepository) {
        this.sdaCourseRepository = sdaCourseRepository;
        this.technologyRepository = technologyRepository;
    }

    public void addTechnologyToCourse(Integer courseId, Integer technologyId) {
        SdaCourse course = sdaCourseRepository.findCourseByIdWithTechnologies(courseId).orElseThrow();
        Technology technology = technologyRepository.findById(technologyId).orElseThrow();
        if (course.getTechnologies().contains(technology)) {
            throw new RuntimeException("Technology already linked with this course"); // lepiej wyrzucić wyjątek własnego typu
        }
        course.addTechnology(technology);
        //sdaCourseRepository.update(course);
    }
}
