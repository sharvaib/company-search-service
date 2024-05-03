package com.spring.exercise.companysearchservice.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.spring.exercise.companysearchservice.CompanySearchServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles(value = "integration")
@SpringBootTest(classes = CompanySearchServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@AutoConfigureMockMvc
class CompanySearchServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void init(@Value("${wiremock-config.port}") int port) {
        wireMockServer = new WireMockServer(port);
        wireMockServer.start();
    }

    @ParameterizedTest
    @CsvSource({"__files/request/only-company-name.json,expected-response/3-active-companies.json,true",
                "__files/request/only-company-name.json,expected-response/4-total-companies.json,false",
                "__files/request/company-number-with-resigned-officer.json,expected-response/company-09670032-only-active-officers.json,false",
                "__files/request/company-from-db.json,expected-response/company-06500244.json,false"})
    void searchCompanies(String requestFilePath, String expectedResponseFilePath, Boolean onlyActiveCompanies) throws Exception {
        String actualResponse = mockMvc.perform(get("/companies/search")
                        .contentType("application/json")
                        .queryParam("OnlyActiveCompanies", onlyActiveCompanies.toString())
                        .header("x-api-key", "random-access-key")
                        .content(getFileData(requestFilePath))
                )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(
                getFileData(expectedResponseFilePath), actualResponse, JSONCompareMode.LENIENT);
    }

    @Test
    void searchCompaniesInvalidBackendJsonResponse() throws Exception {
        mockMvc.perform(get("/companies/search")
                        .contentType("application/json")
                        .queryParam("OnlyActiveCompanies", "true")
                        .header("x-api-key", "random-access-key")
                        .content(getFileData("__files/request/expect-incorrect-json.json"))
                )
                .andExpect(status().isInternalServerError()).andReturn().getResponse().getContentAsString();
    }

    private String getFileData(String filePath) throws IOException {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        Path pathToFile = resourceDirectory.resolve(filePath);
        return Files.readString(pathToFile);
    }

    @AfterAll
    static void stopWireMockServer() {
        wireMockServer.stop();
    }

}
