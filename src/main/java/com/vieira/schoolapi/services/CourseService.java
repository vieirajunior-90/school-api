package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.CourseDto;
import com.vieira.schoolapi.models.Course;
import com.vieira.schoolapi.repositories.CourseRepository;
import com.vieira.schoolapi.services.exceptions.ConstraintException;
import com.vieira.schoolapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Course save(Course discipline) {
        if (courseRepository.existsByName(discipline.getName())) {
            throw new ConstraintException("Discipline already exists");
        }
        return courseRepository.save(discipline);
    }

    public CourseDto findById(Long id) {
        Optional<Course> discipline = courseRepository.findById(id);
        return CourseDto.convert(discipline.orElseThrow((() -> new ResourceNotFoundException(id))));
    }

    public List<CourseDto> findAll() {
        List<Course> disciplines = courseRepository.findAll();
        return disciplines.stream().map(CourseDto::convert).collect(Collectors.toList());
    }
}
