package com.vieira.schoolapi.repositories;

import com.vieira.schoolapi.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsByStreet(String street);
    boolean existsByNumber(String number);
}
