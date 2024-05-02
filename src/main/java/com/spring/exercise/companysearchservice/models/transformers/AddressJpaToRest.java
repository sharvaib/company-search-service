package com.spring.exercise.companysearchservice.models.transformers;

import com.spring.exercise.companysearchservice.models.rest.Address;

public class AddressJpaToRest {

    public static Address convert(com.spring.exercise.companysearchservice.models.jpa.Address address) {
        return address == null? null : Address.builder()
                .addressLine1(address.getAddressLine1())
                .country(address.getCountry())
                .locality(address.getLocality())
                .premises(address.getPremises())
                .postalCode(address.getPostalCode()).build();
    }
}
