package com.vieira.schoolapi.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    @NonNull
    private Integer status;
    @NonNull
    private String error;
    @NonNull
    private String message;
    @NonNull
    private String path;

}
