package com.vieira.schoolapi.config;

import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args){
        Student S1 = Student.builder()
                .name("Harry Potter")
                .email("harrypotter@outlook.com")
                .phone("(99)99999-9999")
                .password("!@1234Abcd")
                .build();

        Student S2 = Student.builder()
                .name("Hermione Granger")
                .email("hermionegranger@outlook.com")
                .phone("(99)88888-8888")
                .password("!@4321Abcd")
                .build();

        Student S3 = Student.builder()
                .name("Ron Weasley")
                .email("ronweasley@outlook.com")
                .phone("(99)66666-8888")
                .password("!@8888Abcd")
                .build();

        Student S4 = Student.builder()
                .name("Albus Dumbledore")
                .email("dumbledore@gmail.com")
                .phone("(22)44444-8888")
                .password("!@4321Zxcv")
                .build();

        studentRepository.saveAll(Arrays.asList(S1, S2, S3, S4));
    }
}
