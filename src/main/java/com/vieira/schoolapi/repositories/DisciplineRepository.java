package com.vieira.schoolapi.repositories;

import com.vieira.schoolapi.models.Discipline;
import com.vieira.schoolapi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    boolean existsByName(String name);
}
