package com.spring.exercise.companysearchservice.models.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Company {

    @Id
    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "company_type")
    private String companyType;
    private String title;
    @Column(name = "company_status")
    private String companyStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany
    @JoinColumn(name = "company_number")
    private List<Officer> officers;

}
