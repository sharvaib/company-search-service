package com.spring.exercise.companysearchservice.service;

import com.spring.exercise.companysearchservice.models.jpa.Company;
import com.spring.exercise.companysearchservice.models.transformers.CompanyRestToJpa;
import com.spring.exercise.companysearchservice.repository.AddressRepository;
import com.spring.exercise.companysearchservice.repository.CompanyRepository;
import com.spring.exercise.companysearchservice.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompaniesRepositoryManager {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OfficerRepository officerRepository;

    protected Company searchInRepo(String companyNumber) {
        return companyRepository.findByCompanyNumber(companyNumber);
    }

    protected void saveCompanies(List<com.spring.exercise.companysearchservice.models.rest.Company> companies) {
        companies.forEach(company -> {
            if(searchInRepo(company.getCompanyNumber())==null) {
                Company companyJpa = CompanyRestToJpa.convert(company);
                addressRepository.save(companyJpa.getAddress());
                companyRepository.save(companyJpa);
                companyJpa.getOfficers().forEach(officer -> {
                    addressRepository.save(officer.getAddress());
                    officerRepository.save(officer);
                });
            }
        });
    }

}
