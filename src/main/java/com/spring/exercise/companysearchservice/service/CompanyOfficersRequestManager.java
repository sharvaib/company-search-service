package com.spring.exercise.companysearchservice.service;

import com.spring.exercise.companysearchservice.models.rest.CompaniesSearchResponse;
import com.spring.exercise.companysearchservice.models.rest.Company;
import com.spring.exercise.companysearchservice.models.rest.CompanySearchRequest;
import com.spring.exercise.companysearchservice.models.rest.Officer;
import com.spring.exercise.companysearchservice.truproxy.TruProxyRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyOfficersRequestManager {

    @Autowired
    private TruProxyRestClient truProxyRestClient;

    public List<Officer> getOfficers(String companyNumber, String keyHeaderValue) {
        List<Officer> officers = truProxyRestClient.getOfficers(keyHeaderValue, companyNumber).getItems();
        return officers == null? new ArrayList<>() : officers.stream().filter(officer -> officer.getResignedOn() == null).toList();
    }

    public CompaniesSearchResponse populateOfficers(CompaniesSearchResponse companiesSearchResponse, String keyHeaderValue) {
        companiesSearchResponse.getItems().forEach(company ->  company.setOfficers(getOfficers(company.getCompanyNumber(), keyHeaderValue)));
        return companiesSearchResponse;
    }
}
