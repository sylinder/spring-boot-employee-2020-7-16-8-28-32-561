package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void should_return_2_companies_when_get_all_companies_given_2_companies_in_total() {
        // given
        List<Company> companies = Collections.nCopies(2, new Company());
        Mockito.when(companyRepository.getAllCompany()).thenReturn(companies);

        // when
        List<Company> companiesQueried = companyService.getAllCompanies();

        // then
        assertEquals(2, companiesQueried.size());
    }

//    /**
//     * given
//     *      page: 2, pageSize: 2 , companies list contains 5 companies
//     * when
//     *      get companies by page and pageSize
//     * then:
//     *      return companies in index 2 and 3
//     */
//
    @Test
    void should_return_companies_ids_2_and_3_when_get_companies_by_page_and_pageSize_given_page_pageSize_and_5_companies_in_list() {
        int page = 2, pageSize = 2;
        List<Company> companies = new ArrayList<>();
        for (int index = 0; index < 5; index++) {
            Company company = new Company();
            company.setId(index);
            companies.add(company);
        }

        Mockito.when(companyRepository.getAllCompany()).thenReturn(companies);

        // when
        List<Company> companiesQueried = companyService.getCompaniesByRange(page, pageSize);
        assertEquals(2, companiesQueried.size());
        assertEquals(2, companiesQueried.get(0).getId());
        assertEquals(3, companiesQueried.get(1).getId());
    }


}