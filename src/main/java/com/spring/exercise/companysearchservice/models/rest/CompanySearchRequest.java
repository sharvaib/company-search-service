package com.spring.exercise.companysearchservice.models.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanySearchRequest {
    private String companyName;
    private String companyNumber;
}
