package com.vieira.schoolapi.controllers;

import com.vieira.schoolapi.dtos.AddressDto;
import com.vieira.schoolapi.dtos.StudentDto;
import com.vieira.schoolapi.models.Address;
import com.vieira.schoolapi.models.Student;
import com.vieira.schoolapi.services.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Api("Address Controller")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    @ApiOperation("Create a new address")
    public ResponseEntity<Object> save(@Valid @RequestBody AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(address));
    }

    @GetMapping("address/{id}")
    @ApiOperation("Find a address by id")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
    }

    @GetMapping("/addresses")
    @ApiOperation("Find all addresses")
    public ResponseEntity<Page<AddressDto>> findAll(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll(pageable));
    }


    @PutMapping("address/{id}")
    @ApiOperation("Update a address")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        Address addressToUpdate = addressService.update(id, addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(AddressDto.convert(addressToUpdate));
    }

    @DeleteMapping("address/{id}")
    @ApiOperation("Delete a address")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
