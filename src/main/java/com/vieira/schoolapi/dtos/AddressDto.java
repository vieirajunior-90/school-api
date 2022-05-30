package com.vieira.schoolapi.dtos;

import com.vieira.schoolapi.models.Address;
import com.vieira.schoolapi.models.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public record AddressDto(
        Long id,

        @NotBlank(message = "The street is required")
        String street,

        @NotBlank(message = "The number is required")
//        @Pattern(regexp = "^[/d]{1,5}$", message = "The number must be between 1 and 5 digits")
        String number,

        String complement,
        String district,

        @NotBlank(message = "The city is required")
        String city,

        @NotBlank(message = "The state is required")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Invalid state")
        String state,

        String country,

        @NotBlank(message = "The zip code is required")
//        @Pattern(regexp = "^[/d]{5}-[/d]{3}$", message = "Invalid zip code")
        String zipCode,

        List<StudentDto> students
) implements Serializable {

    public static AddressDto convert(Address address) {
        List<StudentDto> students = new ArrayList<>();
        for (Student student : address.getStudents()) {
            students.add(StudentDto.convert(student));
        }
        return new AddressDto(
                address.getId(),
                address.getStreet(), address.getNumber(),
                address.getComplement(), address.getDistrict(),
                address.getCity(), address.getState(),
                address.getCountry(), address.getZipCode(),
                students);
    }
}
