package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.CourseDto;
import com.vieira.schoolapi.models.Course;
import com.vieira.schoolapi.services.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Api("Course Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    @ApiOperation("Create a new course")
    public ResponseEntity<Object> save(@Valid @RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
        }
        catch(ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getConstraintViolations());
        }
    }

    @GetMapping("/course/{id}")
    @ApiOperation("Find a course by id")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(id));
    }

    @GetMapping("/courses")
    @ApiOperation("Find all courses")
    public ResponseEntity<List<CourseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @PutMapping("/course/{id}")
    @ApiOperation("Update a course")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody Course course) {
        Course courseToUpdate = courseService.update(id, course);
        return ResponseEntity.status(HttpStatus.OK).body(CourseDto.convert(courseToUpdate));
    }

    @DeleteMapping("/course/{id}")
    @ApiOperation("Delete a course")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
