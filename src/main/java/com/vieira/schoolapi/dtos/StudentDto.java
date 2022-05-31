package com.vieira.schoolapi.dtos;

import com.vieira.schoolapi.models.Address;
import com.vieira.schoolapi.models.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public record StudentDto(
        Long id,
        @NotBlank(message = "Name is required")
        @Size(min = 1)
        String name,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Phone is required")
        @Size(min = 8, max = 20)
        String phone,
        List<CourseDto> courses){

    public static StudentDto convert(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getCourses().stream().map(CourseDto::convert).collect(Collectors.toList()));
    }
}
