package com.vieira.schoolapi.config;

import com.vieira.schoolapi.dtos.AddressDto;
import com.vieira.schoolapi.models.Address;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.repositories.AddressRepository;
import com.vieira.schoolapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args){
        addStudents();
    }

    public void addStudents(){
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

        List<Address> addresses = addAddress();

        S1.setAddress(addresses.get(0));
        S2.setAddress(addresses.get(1));
        studentRepository.saveAll(Arrays.asList(S1, S2));
    }

    public List<Address> addAddress(){
        Address A1 = Address.builder()
                .street("Privet Drive")
                .number("4")
                .complement("The cupboard under the stairs")
                .district("Little Whinging")
                .city("London")
                .state("EN")
                .country("United Kingdom")
                .zipCode("12345-6789")
                .build();

        Address A2 = Address.builder()
                .street("Heathgate")
                .number("8")
                .complement(null)
                .district("Hampstead Garden Suburb")
                .city("London")
                .state("EN")
                .country("United Kingdom")
                .zipCode("90890-0987")
                .build();

        addressRepository.saveAll(Arrays.asList(A1, A2));

        return Arrays.asList(A1, A2);
    }

}
