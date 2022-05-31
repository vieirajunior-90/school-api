package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.DisciplineDto;
import com.vieira.schoolapi.models.Discipline;
import com.vieira.schoolapi.services.DisciplineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
@Api("Student Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @PostMapping("/discipline")
    @ApiOperation("Create a new discipline")
    public ResponseEntity<Object> save(@Valid @RequestBody Discipline discipline) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(disciplineService.save(discipline));
        }
        catch(ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(e.getConstraintViolations());
        }
    }

    @GetMapping("/discipline/{id}")
    @ApiOperation("Find a discipline by id")
    public ResponseEntity<DisciplineDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(disciplineService.findById(id));
    }

    @GetMapping("/disciplines")
    @ApiOperation("Find all disciplines")
    public ResponseEntity<List<DisciplineDto>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(disciplineService.findAll());
    }
}
