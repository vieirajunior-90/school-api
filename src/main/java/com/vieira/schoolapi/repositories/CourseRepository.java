package com.vieira.schoolapi.repositories;

import com.vieira.schoolapi.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByName(String name);
}
