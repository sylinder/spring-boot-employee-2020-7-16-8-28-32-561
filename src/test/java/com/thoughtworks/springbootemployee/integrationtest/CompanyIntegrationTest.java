package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_get_companies() throws Exception {
        mockMvc.perform(get("/companies")).andExpect(status().isOk());
    }

    @Test
    void should_return_1_company_when_add_company_given_1_company() throws Exception {
        String companyPayload = "{\"name\": \"oocl\"}";
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyPayload))
                .andExpect(status().isCreated());

        List<Company> companies = companyRepository.findAll();
        assertEquals(1,companies.size());
    }

    @Test
    void should_return_0_company_when_delete_company_given_company_id() throws Exception {
        Company company = new Company();
        company.setId(1);
        company.setName("oocl");
        companyRepository.save(company);
        mockMvc.perform(delete("/companies/1")).andExpect(status().isOk());
        List<Company> companies = companyRepository.findAll();
        assertEquals(0, companies.size());
    }

}
