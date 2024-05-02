package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.rest.CompaniesSearchResponse;
import com.spring.exercise.companysearchservice.models.rest.Company;

import java.util.Collections;

public class CompanyJpaToRest {

    public static CompaniesSearchResponse transform(com.spring.exercise.companysearchservice.models.jpa.Company company) {
        return CompaniesSearchResponse.builder().totalResults(1)
                .items(Collections.singletonList(Company.builder().companyNumber(company.getCompanyNumber())
                        .companyStatus(company.getCompanyStatus())
                        .companyType(company.getCompanyType())
                        .title(company.getTitle())
                        .dateOfCreation(company.getDateOfCreation())
                        .address(AddressJpaToRest.convert(company.getAddress()))
                        .officers(OfficerJpaToRest.convert(company.getOfficers()))
                        .build())).build();
    }
}
