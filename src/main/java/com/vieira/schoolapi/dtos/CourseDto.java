package com.vieira.schoolapi.dtos;

import com.vieira.schoolapi.models.Course;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record CourseDto(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description

) implements Serializable {

        public static CourseDto convert(Course discipline) {
                return new CourseDto(
                        discipline.getId(),
                        discipline.getName(),
                        discipline.getDescription()
                );
        }
}
