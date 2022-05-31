package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.CourseDto;
import com.vieira.schoolapi.models.Course;
import com.vieira.schoolapi.services.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Api("Student Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/discipline")
    @ApiOperation("Create a new discipline")
    public ResponseEntity<Object> save(@Valid @RequestBody Course discipline) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(discipline));
        }
        catch(ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getConstraintViolations());
        }
    }

    @GetMapping("/discipline/{id}")
    @ApiOperation("Find a discipline by id")
    public ResponseEntity<CourseDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(id));
    }

    @GetMapping("/disciplines")
    @ApiOperation("Find all disciplines")
    public ResponseEntity<List<CourseDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }
}
