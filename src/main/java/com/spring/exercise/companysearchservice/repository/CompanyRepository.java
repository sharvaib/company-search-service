package com.spring.exercise.companysearchservice.repository;


import com.spring.exercise.companysearchservice.models.jpa.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String> {
  Company findByCompanyNumber(String companyNumber);
}

