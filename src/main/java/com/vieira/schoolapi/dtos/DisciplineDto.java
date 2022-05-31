package com.vieira.schoolapi.dtos;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record DisciplineDto(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description

) implements Serializable {
}
