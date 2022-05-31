package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.CourseDto;
import com.vieira.schoolapi.dtos.StudentDto;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.services.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Api("Student Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    @ApiOperation("Create a new student")
    public ResponseEntity<Object> save(@RequestBody Student student) {
        try {
            @Valid StudentDto studentDto = new StudentDto(
                    student.getId(),
                    student.getName(),
                    student.getEmail(),
                    student.getPhone(),
                    student.getCourses().stream().map(CourseDto::convert).collect(Collectors.toList())
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

    @GetMapping("student/{id}")
    @ApiOperation("Find a student by id")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id));
    }

    @GetMapping("/student")
    @ApiOperation("Find a student by name")
    public ResponseEntity<List<StudentDto>> findByName(@RequestParam(value="name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findByName(name));
    }

    @GetMapping("/students")
    @ApiOperation("Find all students")
    public ResponseEntity<Page<StudentDto>> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Student> students = studentService.findAll(pageable);
        Page<StudentDto> studentDtos = students.map(StudentDto::convert);
        return ResponseEntity.status(HttpStatus.OK).body(studentDtos);
    }
    @PutMapping("student/{id}")
    @ApiOperation("Update a student")
    public ResponseEntity<StudentDto> update(@PathVariable Long id, @RequestBody Student student) {
        Student studentToUpdate = studentService.update(id, student);
        return ResponseEntity.status(HttpStatus.OK).body(StudentDto.convert(studentToUpdate));
    }

    @DeleteMapping("student/{id}")
    @ApiOperation("Delete a student")
    public ResponseEntity<StudentDto> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
