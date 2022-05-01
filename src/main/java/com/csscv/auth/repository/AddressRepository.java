package com.csscv.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csscv.auth.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
