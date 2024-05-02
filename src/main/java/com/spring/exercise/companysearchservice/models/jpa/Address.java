package com.spring.exercise.companysearchservice.models.jpa;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "premises")
    private String premises;
    @Column(name = "locality")
    private String locality;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "country")
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
}
