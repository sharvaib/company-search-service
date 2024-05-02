package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.jpa.Address;
import com.spring.exercise.companysearchservice.models.jpa.Officer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfficerJpaToRestTest {

    private final List<Officer> inputOfficers = new ArrayList<>();

    @BeforeEach
    void setup() {
        inputOfficers.add(Officer.builder()
                .officerRole("Director")
                .name("first name")
                .appointedOn(Date.valueOf("2001-11-12"))
                .companyNumber("122321")
                .resignedOn(Date.valueOf("2021-12-11"))
                .address(Address.builder()
                        .postalCode("SW23AD")
                        .premises("first")
                        .addressLine1("1 line address")
                        .locality("local")
                        .country("UK")
                        .id(234L)
                        .build()).build());
    }

    @Test
    @DisplayName("Test Officer transformer from jpa to rest model")
    void testConversion() {
        List<com.spring.exercise.companysearchservice.models.rest.Officer> officerList = OfficerJpaToRest.convert(inputOfficers);
        assertNotNull(officerList);
        assertEquals(inputOfficers.size(), officerList.size());
        assertEquals("first name", officerList.get(0).getName());
        assertEquals("Director", officerList.get(0).getOfficerRole());
        assertEquals(Date.valueOf("2021-12-11"), officerList.get(0).getResignedOn());
        assertEquals(Date.valueOf("2001-11-12"), officerList.get(0).getAppointedOn());
        assertNotNull(officerList.get(0).getAddress());
        assertEquals("SW23AD", officerList.get(0).getAddress().getPostalCode());
        assertEquals("first", officerList.get(0).getAddress().getPremises());
        assertEquals("1 line address", officerList.get(0).getAddress().getAddressLine1());
        assertEquals("local", officerList.get(0).getAddress().getLocality());
        assertEquals("UK", officerList.get(0).getAddress().getCountry());
    }

    @Test
    @DisplayName("Test Officer transformer from jpa to rest model, when officer.address is null")
    void testConversionWithNullAddress() {
        inputOfficers.get(0).setAddress(null);
        List<com.spring.exercise.companysearchservice.models.rest.Officer> officerList = OfficerJpaToRest.convert(inputOfficers);
        assertNotNull(officerList);
        assertEquals(inputOfficers.size(), officerList.size());
        assertEquals("first name", officerList.get(0).getName());
        assertEquals("Director", officerList.get(0).getOfficerRole());
        assertEquals(Date.valueOf("2021-12-11"), officerList.get(0).getResignedOn());
        assertEquals(Date.valueOf("2001-11-12"), officerList.get(0).getAppointedOn());
        assertNull(officerList.get(0).getAddress());
    }

}