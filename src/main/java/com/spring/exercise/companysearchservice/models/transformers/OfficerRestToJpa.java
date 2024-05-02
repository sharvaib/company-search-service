package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.jpa.Officer;

import java.util.ArrayList;
import java.util.List;

public class OfficerRestToJpa {
    public static List<Officer> convert(String companyNumber, List<com.spring.exercise.companysearchservice.models.rest.Officer> officers) {

        List<Officer> officerList = new ArrayList<>();
        officers.forEach(officer -> officerList.add(Officer.builder()
                .officerRole(officer.getOfficerRole())
                .name(officer.getName())
                .appointedOn(officer.getAppointedOn())
                .resignedOn(officer.getResignedOn())
                .companyNumber(companyNumber)
                .address(AddressRestToJpa.convert(officer.getAddress()))
                .build())
        );

        return officerList;
    }
}
