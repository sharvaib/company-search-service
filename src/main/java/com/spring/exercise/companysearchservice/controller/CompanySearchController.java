package com.spring.exercise.companysearchservice.controller;

import com.spring.exercise.companysearchservice.models.rest.CompanySearchRequest;
import com.spring.exercise.companysearchservice.service.CompaniesSearchRequestManager;
import com.spring.exercise.companysearchservice.models.rest.CompaniesSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanySearchController {

  @Autowired
  CompaniesSearchRequestManager companiesSearchRequestManager;

  @GetMapping("/search")
  public ResponseEntity<CompaniesSearchResponse> searchCompanies(@RequestHeader("x-api-key") String keyValue,
                                                                 @RequestParam(name = "OnlyActiveCompanies") boolean onlyActiveCompanies,
                                                                 @RequestBody() CompanySearchRequest companySearchRequest) {
    try {
      return new ResponseEntity<>(companiesSearchRequestManager
              .searchCompanies(keyValue, onlyActiveCompanies, companySearchRequest), HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
