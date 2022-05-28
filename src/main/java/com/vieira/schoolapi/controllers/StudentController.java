package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.StudentDto;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Student student) {
        try {
            @Valid StudentDto studentDto = new StudentDto(
                    student.getName(),
                    student.getEmail(),
                    student.getPhone()
            );
        Student studentToSave = new Student();
        BeanUtils.copyProperties(studentDto, studentToSave);
        studentToSave.setPassword(student.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentToSave));
    }
        catch(ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getConstraintViolations());
        }
    }
}
