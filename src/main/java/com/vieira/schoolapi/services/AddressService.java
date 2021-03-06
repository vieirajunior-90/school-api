package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.AddressDto;
import com.vieira.schoolapi.models.Address;
import com.vieira.schoolapi.repositories.AddressRepository;
import com.vieira.schoolapi.services.exceptions.ConstraintException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Address save(Address address) {
        if(addressRepository.existsByStreet(address.getStreet())
                && addressRepository.existsByNumber(address.getNumber())) {
            throw new ConstraintException("Address already exists");
        }
        return addressRepository.save(address);
    }

    public AddressDto findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return AddressDto.convert(address.orElseThrow((() -> new ConstraintException("Address not found"))));
    }

    public Page<AddressDto> findAll(Pageable pageable) {
        Page<Address> addresses = addressRepository.findAll(pageable);
        Page<AddressDto> addressDtos = addresses.map(AddressDto::convert);
        return addressDtos;
    }

    @Transactional
    public Address update(Long id, AddressDto addressDto) {
        Optional<Address> addressToUpdate = addressRepository.findById(id);
        if(!addressToUpdate.isPresent()) {
            throw new ConstraintException("Address not found");
        }
        BeanUtils.copyProperties(addressDto, addressToUpdate.get());
        return addressRepository.save(addressToUpdate.get());
    }
    @Transactional
    public void delete(Long id) {
        Optional<Address> addressToDelete = addressRepository.findById(id);
        if(!addressToDelete.isPresent()) {
            throw new ConstraintException("Address not found");
        }
        addressRepository.delete(addressToDelete.get());
    }
}
