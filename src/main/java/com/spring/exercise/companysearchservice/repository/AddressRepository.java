package com.spring.exercise.companysearchservice.repository;


import com.spring.exercise.companysearchservice.models.jpa.Address;
import com.spring.exercise.companysearchservice.models.jpa.Company;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}

