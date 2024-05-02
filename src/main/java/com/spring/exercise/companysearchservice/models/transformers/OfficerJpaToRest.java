package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.rest.Officer;

import java.util.ArrayList;
import java.util.List;

public class OfficerJpaToRest {
    public static List<Officer> convert(List<com.spring.exercise.companysearchservice.models.jpa.Officer> officers) {
        List<Officer> officerList = new ArrayList<>();
        officers.forEach(officer -> officerList.add(Officer.builder()
                .officerRole(officer.getOfficerRole())
                .name(officer.getName())
                .appointedOn(officer.getAppointedOn())
                .resignedOn(officer.getResignedOn())
                .address(AddressJpaToRest.convert(officer.getAddress()))
                .build())
        );

        return officerList;
    }
}
