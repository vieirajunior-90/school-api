package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.CourseDto;
import com.vieira.schoolapi.models.Course;
import com.vieira.schoolapi.models.Student;
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
    public Course save(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new ConstraintException("Course already exists");
        }
        return courseRepository.save(course);
    }

    public CourseDto findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        return CourseDto.convert(course.orElseThrow((() -> new ResourceNotFoundException(id))));
    }

    public List<CourseDto> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseDto::convert).collect(Collectors.toList());
    }

    @Transactional
    public Course update(Long id, Course course) {
        Optional<Course> courseToUpdate = courseRepository.findById(id);
        if(courseToUpdate.isEmpty()){
            throw new ResourceNotFoundException(id);
        }
        course.setName(courseToUpdate.get().getName());
        course.setDescription(courseToUpdate.get().getDescription());
        course.setStudents(courseToUpdate.get().getStudents());
        return courseRepository.save(course);
    }

    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
