package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.StudentDto;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.repositories.StudentRepository;
import com.vieira.schoolapi.services.exceptions.ConstraintException;
import com.vieira.schoolapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDto findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return StudentDto.convert(student.orElseThrow((() -> new ResourceNotFoundException(id))));
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
}
