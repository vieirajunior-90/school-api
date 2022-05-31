package com.vieira.schoolapi.repositories;

import com.vieira.schoolapi.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
