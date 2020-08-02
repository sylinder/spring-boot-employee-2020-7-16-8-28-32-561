package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void tearDown() {
        employeeRepository.deleteAll();
        employeeRepository.flush();
    }

    @Test
    void should_return_ok_when_get_employees() throws Exception {
        //when - then
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void should_return_xiaoming_when_get_employee_by_id_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee();
        employee.setName("xiaoming");
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company); //for company id not null
        employee.setCompany(companySaved);
        Employee employeeSaved = employeeRepository.save(employee);
        int employeeId = employeeSaved.getId();

        //when
        mockMvc.perform(get(String.format("/employees/%d", employeeId)))
                .andExpect(status().isOk());

        //then
        EmployeeResponse returningEmployee = employeeService.getEmployeeById(employeeId);
        assertEquals("xiaoming", returningEmployee.getName());
    }

    @Test
    void should_return_2_when_get_employees_by_gender_male_given_2_male_and_1_female() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company);
        List<Employee> employees = new ArrayList<>();
        for (int index = 0; index < 3; index++) {
            Employee employee = new Employee();
            employee.setName("aha");
            employee.setCompany(company);
            employee.setGender("male");
            employees.add(employee);
        }
        employees.get(2).setGender("female");
        employeeRepository.saveAll(employees);

        //when
        String gender = "male";
        mockMvc.perform(get(String.format("/employees?gender=%s", "gender")))
                .andExpect(status().isOk());

        //then
        List<EmployeeResponse> employeeResponseList = employeeService.getEmployeeByGender(gender);
        assertEquals(2, employeeResponseList.size());

    }
}
