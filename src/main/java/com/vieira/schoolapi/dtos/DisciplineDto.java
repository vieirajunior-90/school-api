package com.vieira.schoolapi.dtos;

import com.vieira.schoolapi.models.Discipline;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record DisciplineDto(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description

) implements Serializable {

        public static DisciplineDto convert(Discipline discipline) {
                return new DisciplineDto(
                        discipline.getId(),
                        discipline.getName(),
                        discipline.getDescription()
                );
        }
}
