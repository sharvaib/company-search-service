package com.spring.exercise.companysearchservice.truproxy;

public class UrlProvider {

    public String getCompaniesSearchUri(String url, String searchTerm) {
        return url.replace("{search_term}", searchTerm);
    }

    public String getOfficersUri(String url, String companyNumber) {
        return url.replace("{company_number}", companyNumber);
    }
}
