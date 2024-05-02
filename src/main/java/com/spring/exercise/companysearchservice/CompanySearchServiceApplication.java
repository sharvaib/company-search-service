package com.spring.exercise.companysearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ConfigurationProperties("app")
public class CompanySearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanySearchServiceApplication.class, args);
    }

}
