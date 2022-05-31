package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.StudentDto;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.repositories.StudentRepository;
import com.vieira.schoolapi.services.exceptions.ConstraintException;
import com.vieira.schoolapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDto findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return StudentDto.convert(student.orElseThrow((() -> new ResourceNotFoundException(id))));
    }

    public List<StudentDto> findByName(String name) {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            String[] names = student.getName().toLowerCase().split(" ");
            if (names[0].equals(name.toLowerCase()) || names[1].equals(name.toLowerCase())) {
                studentDtos.add(StudentDto.convert(student));
            }
        }
        return studentDtos;
    }

    public Page<Student> findAll(Pageable pageable) {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            studentDtos.add(StudentDto.convert(student));
        }
        return studentRepository.findAll(pageable);
    }

    @Transactional
    public Student save(Student student) {
        try{
            if(studentRepository.existsByEmail(student.getEmail())){
                throw new ConstraintException("Email already exists");
            }
            if(studentRepository.existsByPhone(student.getPhone())){
                throw new ConstraintException("Phone already exists");
            }
            return studentRepository.save(student);
        }
        catch (ConstraintViolationException e) {
            throw new ConstraintException(e.getMessage());
        }
    }

    @Transactional
    public Student update(Long id, Student student) {
        Optional<Student> studentToUpdate = studentRepository.findById(id);
        if(!studentToUpdate.isPresent()){
            throw new ResourceNotFoundException(id);
        }
        student.setId(id);
        return studentRepository.save(student);
    }

    @Transactional
    public void delete(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()){
            throw new ResourceNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
