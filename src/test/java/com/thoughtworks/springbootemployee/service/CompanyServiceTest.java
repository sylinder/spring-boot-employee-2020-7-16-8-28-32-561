package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void should_2_employee_response_when_get_employee_by_companyid_given_id_1() {
        //given
        int companyId = 1;
        List<Employee> employees = new ArrayList<>();
        Company company = new Company();
        company.setName("caps");
        company.setId(companyId);
        Employee employee_1 = new Employee();
        employee_1.setCompany(company);
        Employee employee_2 = new Employee();
        employee_2.setCompany(company);

        employees.add(employee_1);
        employees.add(employee_2);
        company.setEmployees(employees);

        when(companyRepository.findById(any())).thenReturn(Optional.of(company));

        //when
        List<EmployeeResponse> employeeResponses = companyService.getEmployeesByCompanyId(companyId);
        //then
        assertEquals(2, employeeResponses.size());

    }
}