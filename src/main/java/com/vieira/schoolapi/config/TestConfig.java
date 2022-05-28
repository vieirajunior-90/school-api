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

        studentRepository.saveAll(Arrays.asList(S1, S2));
    }
}
