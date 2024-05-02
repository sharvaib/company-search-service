package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.jpa.Company;

public class CompanyRestToJpa {

    public static Company convert(com.spring.exercise.companysearchservice.models.rest.Company company) {
        return Company.builder().companyNumber(company.getCompanyNumber())
                .companyStatus(company.getCompanyStatus())
                .companyType(company.getCompanyType())
                .title(company.getTitle())
                .dateOfCreation(company.getDateOfCreation())
                .address(AddressRestToJpa.convert(company.getAddress()))
                .officers(OfficerRestToJpa.convert(company.getCompanyNumber(), company.getOfficers()))
                .build();
    }
}
