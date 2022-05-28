package com.vieira.schoolapi.dtos;

import com.vieira.schoolapi.models.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record StudentDto(
        @NotBlank(message = "Name is required")
        @Size(min = 1)
        String name,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Phone is required")
        @Size(min = 8, max = 20)
        String phone){

    public static StudentDto convert(Student student) {
        return new StudentDto(student.getName(), student.getEmail(), student.getPhone());
    }
}
