package com.spring.exercise.companysearchservice.integration;

import com.spring.exercise.companysearchservice.controller.CompanySearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SmokeTest {

    @Autowired
    private CompanySearchController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
