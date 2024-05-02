package com.spring.exercise.companysearchservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanySearchControllerTest {

    @Autowired
    private CompanySearchController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}