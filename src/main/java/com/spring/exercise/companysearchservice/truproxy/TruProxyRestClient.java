package com.spring.exercise.companysearchservice.truproxy;

import com.spring.exercise.companysearchservice.models.rest.CompaniesSearchResponse;
import com.spring.exercise.companysearchservice.models.rest.CompanyOfficersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TruProxyRestClient {

    public static final String API_KEY = "x-api-key";
    private final String host;
    private final String companiesPath;
    private final String officersPath;

    private final RestTemplate restTemplate;
    private final UrlProvider urlProvider;

    public TruProxyRestClient(@Value("${truproxy.host}") String host,
                              @Value("${truproxy.companies-path}") String companiesPath,
                              @Value("${truproxy.officers-path}") String officersPath) {
        this.host = host;
        this.companiesPath = companiesPath;
        this.officersPath = officersPath;
        restTemplate = new RestTemplate();
        urlProvider = new UrlProvider();
    }

    public CompaniesSearchResponse getCompanies(String keyHeaderValue, String searchTerm) {
        final String uri = urlProvider.getCompaniesSearchUri(host + companiesPath, searchTerm);
        return restTemplate.exchange(
                uri, HttpMethod.GET, getRequestEntity(keyHeaderValue), CompaniesSearchResponse.class).getBody();
    }

    public CompanyOfficersResponse getOfficers(String keyHeaderValue, String companyNumber) {
        final String uri = urlProvider.getOfficersUri(host + officersPath, companyNumber);
        return restTemplate.exchange(
                uri, HttpMethod.GET, getRequestEntity(keyHeaderValue), CompanyOfficersResponse.class).getBody();
    }

    private HttpEntity<?> getRequestEntity(String keyHeaderValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_KEY, keyHeaderValue);
        return new HttpEntity<>(headers);
    }

}
