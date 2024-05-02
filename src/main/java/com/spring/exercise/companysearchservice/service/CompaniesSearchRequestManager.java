package com.spring.exercise.companysearchservice.service;

import com.spring.exercise.companysearchservice.models.rest.*;
import com.spring.exercise.companysearchservice.models.transformers.CompanyJpaToRest;
import com.spring.exercise.companysearchservice.truproxy.TruProxyRestClient;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CompaniesSearchRequestManager {

    Logger logger = LoggerFactory.getLogger(CompaniesSearchRequestManager.class);

    @Autowired
    private TruProxyRestClient truProxyRestClient;

    @Autowired
    private CompanyOfficersRequestManager companyOfficersRequestManager;

    @Autowired
    private CompaniesRepositoryManager companiesRepositoryManager;

    public CompaniesSearchResponse searchCompanies(String keyHeader, Boolean onlyActiveCompanies, CompanySearchRequest searchRequest) {
        com.spring.exercise.companysearchservice.models.jpa.Company company = null;
        if(StringUtils.isNotEmpty(searchRequest.getCompanyNumber())) {
            logger.info("Received company number in request, searching in DB.");
            company = companiesRepositoryManager.searchInRepo(searchRequest.getCompanyNumber());
        }
        if(company == null) {
            logger.info("Sending search request to Tru Api.");
            CompaniesSearchResponse companiesSearchResponse = getFromTruApi(keyHeader, onlyActiveCompanies, searchRequest);
            companiesRepositoryManager.saveCompanies(companiesSearchResponse.getItems());
            return companiesSearchResponse;
        } else {
            logger.info("Returning company information retrieved from DB.");
            return CompanyJpaToRest.transform(company);
        }
    }

    public CompaniesSearchResponse getFromTruApi(String keyHeader, Boolean onlyActiveCompanies, CompanySearchRequest searchRequest) {
        CompaniesSearchResponse companiesSearchResponse = truProxyRestClient.getCompanies(keyHeader, getSearchTerm(searchRequest));
        if(companiesSearchResponse.getItems() != null) {
            filterForActiveFlag(onlyActiveCompanies, companiesSearchResponse);
            return companyOfficersRequestManager.populateOfficers(companiesSearchResponse, keyHeader);
        }
        return companiesSearchResponse;
    }

    private String getSearchTerm(CompanySearchRequest searchRequest) {
        if(StringUtils.isNotEmpty(searchRequest.getCompanyNumber())) {
            return searchRequest.getCompanyNumber();
        }
        return searchRequest.getCompanyName();
    }

    private void filterForActiveFlag(Boolean onlyActiveCompanies, CompaniesSearchResponse companiesSearchResponse) {
        if(onlyActiveCompanies) {
            List<Company> inactiveCompanies = companiesSearchResponse.getItems()
                    .stream().filter(company -> !company.getCompanyStatus().equals("active")).toList();
            companiesSearchResponse.getItems().removeAll(inactiveCompanies);
            companiesSearchResponse.setTotalResults(companiesSearchResponse.getItems().size());
        }
    }
}
