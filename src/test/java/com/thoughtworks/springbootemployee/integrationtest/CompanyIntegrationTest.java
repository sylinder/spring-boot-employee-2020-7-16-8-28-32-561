package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void tearDown() {
        companyRepository.deleteAll();
        companyRepository.flush();
    }

    @Test
    void should_return_ok_when_get_companies() throws Exception {
        //then
        mockMvc.perform(get("/companies")).andExpect(status().isOk());
    }

    @Test
    void should_return_1_company_when_add_company_given_1_company() throws Exception {
        //given
        String companyPayload = "{\"name\": \"oocl\"}";
        //when
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyPayload))
                .andExpect(status().isCreated());

        List<Company> companies = companyRepository.findAll();
        //then
        assertEquals(1, companies.size());
    }

    @Test
    void should_return_0_company_when_delete_company_given_company_id() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company saveCompany = companyRepository.save(company);
        //when
        mockMvc.perform(delete(String.format("/companies/%d", saveCompany.getId()))).andExpect(status().isOk());
        List<Company> companies = companyRepository.findAll();
        //then
        assertEquals(0, companies.size());
    }

    @Test
    void should_return_new_company_when_update_company_by_id_given_a_old_company() throws Exception {
        //given
        Company oldCompany = new Company();
        oldCompany.setName("OOCL");
        Company companySaved = companyRepository.save(oldCompany);
        int id = companySaved.getId();
        String companyPayload = "{\"id\": " + id + ", \"name\": \"Caps\"}";
        //when
        mockMvc.perform(put(String.format("/companies/%d", id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyPayload))
                .andExpect(status().isOk());
        Company newCompany = companyRepository.findById(id).get();
        //then
        assertEquals("Caps", newCompany.getName());
    }


    @Test
    void should_return_oocl_when_get_company_by_id_given_id() throws Exception {
        //given
        Company company = new Company();
        int companyId = 1;
        company.setName("oocl");
        company.setId(companyId);
        companyRepository.save(company);
        //when

        mockMvc.perform(get(String.format("/companies/%d", companyId)))
                .andExpect(status().isOk());

        Company returningCompany = companyRepository.findById(companyId).get();
        assertEquals("oocl", returningCompany.getName());
    }
}
