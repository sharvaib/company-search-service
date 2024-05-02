package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.rest.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressRestToJpaTest {
    private com.spring.exercise.companysearchservice.models.rest.Address inputAddress;

    @BeforeEach
    void setup() {
        inputAddress = Address.builder()
                .postalCode("SW23AD")
                .premises("first")
                .addressLine1("1 line address")
                .locality("local")
                .country("UK")
                .build();
    }

    @Test
    @DisplayName("Test Address transformer from rest to jpa model")
    void testConversion() {
        com.spring.exercise.companysearchservice.models.jpa.Address address = AddressRestToJpa.convert(inputAddress);
        assertNotNull(address);
        assertEquals(0, address.getId());
        assertEquals("SW23AD", address.getPostalCode());
        assertEquals("first", address.getPremises());
        assertEquals("1 line address", address.getAddressLine1());
        assertEquals("local", address.getLocality());
        assertEquals("UK", address.getCountry());
    }

}